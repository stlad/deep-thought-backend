package ru.rtf.rupp.deepthought.controller.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.rtf.rupp.deepthought.dto.MessageDto;
import ru.rtf.rupp.deepthought.service.MessageService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WSMessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;


    @MessageMapping("/broker")
    public void process(@Payload MessageDto message) {
        log.info("Сообщение в чат {}", message.toString());
        // broadcast message to specific chat and save it 
        messageService.saveMessage(message);
        String destination = String.format("/user/messages/%s", message.getChat());
        messagingTemplate.convertAndSend(destination, message);


    }
}
