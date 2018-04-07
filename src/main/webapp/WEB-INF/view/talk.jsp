<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getServerName() + ":"
+ request.getServerPort() + path + "/";
String basePath2 = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <script type="text/javascript" src="<%=basePath2%>resources/js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath2%>resources/js/sockjs-0.3.4.min.js"></script>
    <style>
        textarea {
            height: 300px;
            width: 100%;
            resize: none;
            outline: none;
        }
        input[type=button] {
            float: right;
            margin: 5px;
            width: 50px;
            height: 35px;
            border: none;
            color: white;
            font-weight: bold;
            outline: none;
        }
        .clear {
            background: red;
        }
        .send {
            background: green;
        }
        .clear:active {
            background: yellow;
        }
        .send:active {
            background: yellow;
        }
        .msg {
            width: 100%;
            height: 25px;
            outline: none;
        }
        #content {
            border: 1px solid gray;
            width: 100%;
            height: 400px;
            overflow-y: scroll;
        }
        .from {
            background-color: green;
            width: 80%;
            border-radius: 10px;
            height: 30px;
            line-height: 30px;
            margin: 5px;
            float: left;
            color: white;
            padding: 5px;
            font-size: 22px;
        }
        .to {
            background-color: gray;
            width: 80%;
            border-radius: 10px;
            height: 30px;
            line-height: 30px;
            margin: 5px;
            float: right;
            color: white;
            padding: 5px;
            font-size: 22px;
        }
        .name {
            color: gray;
            font-size: 12px;
        }
        .tmsg_text {
            color: white;
            background-color: rgb(47, 47, 47);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }
        .fmsg_text {
            color: white;
            background-color: rgb(66, 138, 140);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }
        .sfmsg_text {
            color: white;
            background-color: rgb(148, 16, 16);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }
        .tmsg {
            clear: both;
            float: right;
            width: 80%;
            text-align: right;
        }
        .fmsg {
            clear: both;
            float: left;
            width: 80%;
        }
    </style>
    <script>


        var path = '<%=basePath%>';
        var uid=${uid eq null?-1:uid};
        if(uid==-1){
            location.href="<%=basePath2%>";
        }
        var from=uid;
        var fromName='${name}';
        var to=uid==1?2:1;
        var websocket;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://" + path + "/ws?uid="+uid);
        } else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://" + path + "/ws?uid="+uid);
        } else {
            websocket = new SockJS("http://" + path + "/ws/sockjs?uid="+uid);
        }
        websocket.onopen = function(event) {
            console.log("WebSocket:已连接");
            setMessageInnerHTML("open");
            console.log(event);
        };
        websocket.onmessage = function(event) {
            setMessageInnerHTML(event.data);
        };
        websocket.onerror = function(event) {
            console.log("WebSocket:发生错误 ");
            setMessageInnerHTML("WebSocket:发生错误 ");
            console.log(event);
        };
        websocket.onclose = function(event) {
            console.log("WebSocket:已关闭");
            console.log(event);
            setMessageInnerHTML("close");
        }

        //关闭连接
        function closeWebSocket(){
            websocket.close();
        }

        //将消息显示在网页上
        function setMessageInnerHTML(innerHTML){
            document.getElementById('message').innerHTML += innerHTML + '<br/>';
        }

        //发送消息
        function send(){
            var message = document.getElementById('text').value;
            websocket.send(message);
        }

    </script>
</head>
<body>
欢迎：${sessionScope.name }

Welcome<br/>
<input id="text" type="text" />
<button onclick="send()">Send</button>
<button onclick="closeWebSocket()">Close</button>
<div id="message"></div>
</body>
</html>