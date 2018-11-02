<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <!--
	<link rel="stylesheet" href="/css/style.css"/>
    -->
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script type="text/javascript" src="../../js/sockjs.js"></script>
    <script type="text/javascript">
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://localhost:8080/websocket/socketServer");
        }
        else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://localhost:8080/websocket/socketServer");
        }
        else {
            websocket = new SockJS("http://localhost:8080/sockjs/socketServer");
        }
        websocket.onopen = onOpen;
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;

        function onOpen(openEvt) {
            alert(openEvt.Data);
        }

        function onMessage(evt) {
            alert("super is:" + evt.data);
        }
        function onOpen() {//与服务器端连接建立之后被调用
        }
        function onError() {}
        function onClose() {}

        function doSendUser() {

            alert(websocket.readyState + ":" + websocket.OPEN);
            if (websocket.readyState == websocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                websocket.send("#anyone#"+msg);//调用后台handleTextMessage方法
                alert("发送成功!");
            } else {
                alert("连接失败!");
            }
        }


        function doSendUsers() {
            if (websocket.readyState == websocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                websocket.send("#everyone#"+msg);//调用后台handleTextMessage方法
                alert("发送成功!");
            } else {
                alert("连接失败!");
            }
        }


        window.close=function()
        {
            websocket.onclose();
        }
        function websocketClose() {
            websocket.close();
        }
    </script>

</head>
<body>

请输入：<textarea rows="5" cols="10" id="inputMsg" name="inputMsg"></textarea>
<button onclick="doSendUser();">发送</button>
<button onclick="doSendUsers();">群发</button>
<button onclick="websocketClose();">关闭连接</button>
</body>
</html>