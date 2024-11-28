package ru.rtf.rupp.deepthought.controller.rest;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import ru.rtf.rupp.deepthought.dto.MessageDto;
import ru.rtf.rupp.deepthought.service.MessageService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/all") // TODO: limit and offset
    @Operation(summary = "Запрос всех сообщений по ID чата")
    public ResponseEntity<List<MessageDto>> createChat(UUID chatId) {
        List<MessageDto> messages = messageService.getMessagesFromChat(chatId);
        return ResponseEntity.ok(messages);
    }

}
