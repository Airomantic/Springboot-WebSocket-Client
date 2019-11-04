package com.panda.websocket;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;

/**
 * @program: websocket
 * @description:
 * @author: jiangzq
 * @create: 2019-11-04 09:56
 **/
public class WSClient {
    public static void main(final String[] args) {
        final WebSocketClient client = new ReactorNettyWebSocketClient();
        //注意ws://localhost:8080/echo不要写错
        client.execute(URI.create("ws://localhost:8080/echo"), webSocketSession ->
                webSocketSession.send(Flux.just(webSocketSession.textMessage("你好")))
                        .thenMany(webSocketSession.receive().take(1).map(WebSocketMessage::getPayloadAsText))
                        .doOnNext(System.out::println)
                        .then())
                .block(Duration.ofMillis(5000));
    }
}
