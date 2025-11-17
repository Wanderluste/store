package com.cy.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.ai.chat.client.ChatClient;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    @Autowired
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * 非流式：一次性返回文本
     * 访问方式：
     * /chat/test?message=你好
     */
    @GetMapping("/test")
    public String completion(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()         // 同步结果
                .content();     // 获取完整文本
    }

    /**
     * 流式：SSE 实时输出
     * 访问方式：
     * /chat/stream?message=你好
     */
    @GetMapping("/stream")
    public SseEmitter stream(@RequestParam String message) {

        // 设置 0 表示不自动超时
        SseEmitter emitter = new SseEmitter(0L);

        chatClient.prompt()
                .user(message)
                .stream()       // 开启流式
                .content()      // Flux<String>，逐条输出 token
                .doOnNext(token -> {
                    try {
                        emitter.send(SseEmitter.event().data(token));
                    } catch (Exception e) {
                        emitter.completeWithError(e);
                    }
                })
                .doOnComplete(() -> {
                    try {
                        emitter.send(SseEmitter.event().data("[DONE]"));
                    } catch (Exception ignored) { }
                    emitter.complete();
                })
                .subscribe();

        return emitter;
    }
}
