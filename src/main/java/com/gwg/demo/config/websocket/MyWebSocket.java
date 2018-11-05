package com.gwg.demo.config.websocket;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ServerEndpoint这个注解用来标记一个类是 WebSocket 的处理器。然后，你可以在这个类中使用注解@OnOpen,@OnMessage,@OnClose来
 * 表明所修饰的方法是触发事件的回调
 */
@ServerEndpoint(value = "/websocket/socketServer")
@Component
public class MyWebSocket implements InitializingBean, ApplicationContextAware {

    public static int onlineNumber = 0;

    private static ApplicationContext applicationContext;
 
    /**
     * 所有的对象
     */
    public static List<MyWebSocket> webSockets = new CopyOnWriteArrayList<MyWebSocket>();
 
    /**
     * 会话
     */
    private Session session;
 
    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session)
    {
        onlineNumber++;
        webSockets.add(this);
 
        this.session = session;
 
    }
 
    /**
     * 连接关闭
     */
    @OnClose
    public void onClose()
    {
        onlineNumber--;
        webSockets.remove(this);
    }
    int count = 0;;
    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        System.out.println("来自客户端消息：" + message);
 
        sendMessage("欢迎连接");
        
        while(session.isOpen()) {//判断是否连接,或者当有心消息的时候就往前端推送消息
        	try {
        		Double percent = 2.0;
        		
        		if(percent == null) percent = 0.0;

				session.getBasicRemote().sendText(message + "#" + percent);
				Thread.sleep(500);
				if(percent >= 100) {

					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
 
    /**
     * 发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message)
    {
        try
        {
            session.getBasicRemote().sendText(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

	public void afterPropertiesSet() throws Exception {
		System.err.println(22);

	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}

