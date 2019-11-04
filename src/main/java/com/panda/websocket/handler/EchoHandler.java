package com.panda.websocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * @program: websocket
 * @description:  提供服务器的一个返回
 * @author: jiangzq
 * @create: 2019-11-03 15:50
 **/
@Component
public class EchoHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(
                webSocketSession.receive()
                        .map(msg -> webSocketSession.textMessage(
                                "服务端返回：小明，" + msg.getPayloadAsText()
                        ))
        );
    }
}
