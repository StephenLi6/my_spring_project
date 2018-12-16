package com.theshy.websocket;

import org.springframework.web.socket.*;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/12/821:28
 * com.theshy.websocketMyDailyProject
 */
public class FirstWebSocketHandler implements WebSocketHandler {
    //定义一个全局的初始化值count=0
    private static int count = 0;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        while(count <= 100){
            //向前端推送数据，一秒一次推送，格式：1,2,3,4,5.....
            session.sendMessage(new TextMessage(count+""));
            count++;
            Thread.sleep(1000);
        }

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()){
            webSocketSession.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
