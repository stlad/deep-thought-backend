package ru.rtf.rupp.deepthought.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.rtf.rupp.deepthought.dto.ChatMemberInfoDTO;
import ru.rtf.rupp.deepthought.entity.Chat;
import ru.rtf.rupp.deepthought.entity.ChatMemberInfo;
import ru.rtf.rupp.deepthought.entity.User;
import ru.rtf.rupp.deepthought.mapper.ChatMapper;
import ru.rtf.rupp.deepthought.repository.ChatMemberInfoRepository;
import ru.rtf.rupp.deepthought.repository.ChatRepository;
import ru.rtf.rupp.deepthought.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChatMemberInfoRepository chatMemberInfoRepository;

    @Mock
    private ChatMapper chatMapper;

    @InjectMocks
    private ChatService chatService;

    private User user;
    private Chat chat;
    private UUID chatId;
    private String email;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chatId = UUID.randomUUID();
        email = "test@example.com";
        user = new User();
        user.setEmail(email);
        chat = Chat.builder().title("Test Chat").build();
        chat.setId(chatId);
    }

    @Test
    void testAddUser_UserNotFound() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

    }

    @Test
    void testAddUser_ChatNotFound() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(chatRepository.findById(chatId)).thenReturn(Optional.empty());
    }

    @Test
    void testAddUser_UserAlreadyInChat() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(chatRepository.findById(chatId)).thenReturn(Optional.of(chat));
  
    }
    @Test
    void testAddUser_Success() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(chatRepository.findById(chatId)).thenReturn(Optional.of(chat));
        ChatMemberInfo chatMemberInfo = ChatMemberInfo.builder().chat(chat).user(user).build();
        when(chatMemberInfoRepository.save(any(ChatMemberInfo.class))).thenReturn(chatMemberInfo);
        ChatMemberInfoDTO chatMemberInfoDTO = new ChatMemberInfoDTO();
        when(chatMapper.toDTO(any(ChatMemberInfo.class))).thenReturn(chatMemberInfoDTO);

        ChatMemberInfoDTO result = chatService.addUser(email, chatId);

        assertThat(result).isEqualTo(chatMemberInfoDTO);
        verify(chatMemberInfoRepository).save(any(ChatMemberInfo.class));
    }
}