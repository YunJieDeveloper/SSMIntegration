package com.ssm.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/***
 * @Date 2018/3/26
 * @Description  websocket处理类
 * @author zhanghesheng
 * */

@Component
public class WebsocketEndPoint extends TextWebSocketHandler {

    public static final Map<Long, WebSocketSession> userSocketSessionMap;
    static {
        userSocketSessionMap = new HashMap<Long, WebSocketSession>();
    }

    /**
     * 建立连接后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        Long uid = (Long) session.getAttributes().get("uid");
        if (userSocketSessionMap.get(uid) == null) {
            userSocketSessionMap.put(uid, session);
        }
        System.out.println("ConnectionEstablished"+"=>当前在线用户的数量是:"+userSocketSessionMap.size());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        session.sendMessage(returnMessage);
    }

    /**
     * 消息传输错误处理
     */
    @Override
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        Iterator<Map.Entry<Long, WebSocketSession>> it = userSocketSessionMap
                .entrySet().iterator();
    // 移除Socket会话
        while (it.hasNext()) {
            Map.Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }
    /**
     * 关闭连接后
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
        System.out.println("Websocket:" + session.getId() + "已经关闭");
        Iterator<Map.Entry<Long, WebSocketSession>> it = userSocketSessionMap
                .entrySet().iterator();
// 移除Socket会话
        while (it.hasNext()) {
            Map.Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                System.out.println("ConnectionClosed"+"=>当前在线用户的数量是:"+userSocketSessionMap.size());
                break;
            }
        }
    }
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void broadcast(final TextMessage message) throws IOException {
        Iterator<Map.Entry<Long, WebSocketSession>> it = userSocketSessionMap
                .entrySet().iterator();
// 多线程群发
        while (it.hasNext()) {
            final Map.Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
// entry.getValue().sendMessage(message);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }
    /**
     * 给某个用户发送消息
     *
     * @param uid 用户id
     * @param message
     * @throws IOException
     */
    public void sendMessageToUser(Long uid, TextMessage message)
            throws IOException {
        WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }


}
