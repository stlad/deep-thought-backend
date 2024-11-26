package ru.rtf.rupp.deepthought.controller.rest;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import ru.rtf.rupp.deepthought.dto.ChatDto;
import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.dto.ChatCreationDto;
import ru.rtf.rupp.deepthought.service.ChatService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {
    
    @Autowired
    private final ChatService chatService;

    @PostMapping("/create")
    @Operation(summary = "Создание чата")
    public ResponseEntity<ChatDto> createChat(@RequestBody ChatCreationDto chatCreationDto) {
        ChatDto createdChat = chatService.createChat(chatCreationDto);
        return ResponseEntity.ok(createdChat);
    }

    @GetMapping("/all")
    @Operation(summary = "Все чаты пользователя")
    public ResponseEntity<List<ChatDto>> allUserChats(String email) {
        List<ChatDto> chats = chatService.getAllUserChats(email);
        return ResponseEntity.ok(chats);
   }

    @GetMapping("/members/all")
    @Operation(summary = "Все пользователи чата")
    public ResponseEntity<List<UserDTO>> allChatMembers(String title) {
        List<UserDTO> users = chatService.getAllChatMembers(title);
        return ResponseEntity.ok(users);
   }

    @GetMapping("/members/count")
    @Operation(summary = "Все пользователи чата")
    public ResponseEntity<Integer> allChatMembersCount(String title) {
        Integer userCount = chatService.getAllChatMembersCount(title);
        return ResponseEntity.ok(userCount);
   }

   
        
}
