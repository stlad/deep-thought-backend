package ru.rtf.rupp.deepthought.controller.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rtf.rupp.deepthought.dto.MessageDto;
import ru.rtf.rupp.deepthought.entity.Message;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WSMessageController {
    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/broker")
    public void process(@Payload MessageDto message){
        log.info("Сообщение в чат {}", message);


    }
}
