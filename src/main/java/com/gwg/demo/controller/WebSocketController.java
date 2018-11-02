package com.gwg.demo.controller;

import com.gwg.demo.config.websocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 定义一个控制器用来做连接标识和发送消息
 */
@Controller
public class WebSocketController {


    /**
     * 测试页面查看服务能不能通
     */
    @RequestMapping(value = "/")
    public String sayHello(ModelMap modelMap) {
        modelMap.put("msg", "Hello!");
        return "hello";
    }


    /**测试前端给后端推送消息，用户需要登录后操作***********************************/
    @RequestMapping("/websocket/loginPage")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "login";
    }


    @RequestMapping("/websocket/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        System.out.println(username+"登录");
        HttpSession session = request.getSession(false);
        session.setAttribute("SESSION_USERNAME", username); //一般直接保存user实体
        return "send";
    }

    /**测试后端给前端推送消息***********************************/
    //给用户gaoweigang推送消息，前提是用户gaoweigang必须登录了
    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        //webSocketHandler.sendMessage("你好，测试！！！！");
        return username;
    }


    @RequestMapping("/websocket/broad")
    @ResponseBody
    public  String broad() {
        //webSocketHandler.sendMessageToUsers(new TextMessage("发送一条小Broad"));
        System.out.println("群发成功");
        return "broad";
    }

}
