package ru.rtf.rupp.deepthought.controller.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.rtf.rupp.deepthought.entity.Message;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WSMessageController {
    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat/{chatId}")
    public void process(UUID chatID, @Payload Message message){
        log.info("Сообщение в чат {}", chatID);

    }
}
