package com.gwg.demo.controller;

import com.gwg.demo.config.SpringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 定义一个控制器用来做连接标识和发送消息
 */
@RestController
public class WebSocketController {

   @Autowired
   private SpringWebSocketHandler springWebSocketHandler;

    @RequestMapping("/websocket/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        System.out.println(username+"登录");
        HttpSession session = request.getSession(false);
        session.setAttribute("SESSION_USERNAME", username);
        //response.sendRedirect("/quicksand/jsp/websocket.jsp");
        return new ModelAndView("websocket");
    }

    @RequestMapping("/websocket/send")
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        springWebSocketHandler.sendMessageToUser(username, new TextMessage("你好，测试！！！！"));
        return null;
    }
}
