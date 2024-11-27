package ru.rtf.rupp.deepthought.controller.rest;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import ru.rtf.rupp.deepthought.dto.ChatDTO;
import ru.rtf.rupp.deepthought.dto.ChatMemberInfoDTO;
import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.dto.ChatCreationDTO;
import ru.rtf.rupp.deepthought.service.ChatService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {
    
    private final ChatService chatService;

    @PostMapping("/create")
    @Operation(summary = "Создание чата")
    public ResponseEntity<ChatDTO> createChat(@RequestBody ChatCreationDTO chatCreationDto) {
        ChatDTO createdChat = chatService.createChat(chatCreationDto);
        return ResponseEntity.ok(createdChat);
    }

    @GetMapping("/all")
    @Operation(summary = "Все чаты пользователя")
    public ResponseEntity<List<ChatDTO>> allUserChats(String email) {
        List<ChatDTO> chats = chatService.getAllUserChats(email);
        return ResponseEntity.ok(chats);
   }

    @GetMapping("/members/all")
    @Operation(summary = "Все пользователи чата")
    public ResponseEntity<List<UserDTO>> allChatMembers(UUID id) {
        List<UserDTO> users = chatService.getAllChatMembers(id);
        return ResponseEntity.ok(users);
   }

    @GetMapping("/members/count")
    @Operation(summary = "Все пользователи чата")
    public ResponseEntity<Integer> allChatMembersCount(UUID id) {
        Integer userCount = chatService.getAllChatMembersCount(id);
        return ResponseEntity.ok(userCount);
   }

    @PostMapping("/add")
    @Operation(summary = "доабвление пользователя в чат")
    public ResponseEntity<ChatMemberInfoDTO> addUser(String email, UUID chatID) {
        ChatMemberInfoDTO chatMember = chatService.addUser(email, chatID);
        return ResponseEntity.ok(chatMember);
    }

    @PostMapping("/remove")
    @Operation(summary = "Удалить пользователя из чата")
    public ResponseEntity<Boolean> removeUser(String email, UUID chatID) {
        Boolean removed = chatService.removeUser(email, chatID);
        return ResponseEntity.ok(removed);
    }
        
}
