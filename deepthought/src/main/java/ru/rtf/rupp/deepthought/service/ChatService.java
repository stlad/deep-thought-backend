package ru.rtf.rupp.deepthought.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rtf.rupp.deepthought.repository.ChatMemberInfoRepository;
import ru.rtf.rupp.deepthought.repository.ChatRepository;
import ru.rtf.rupp.deepthought.repository.UserRepository;
import ru.rtf.rupp.deepthought.dto.ChatCreationDto;
import ru.rtf.rupp.deepthought.dto.ChatDto;
import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.entity.Chat;
import ru.rtf.rupp.deepthought.entity.ChatMemberInfo;
import ru.rtf.rupp.deepthought.entity.User;
import ru.rtf.rupp.deepthought.mapper.ChatMapper;
import ru.rtf.rupp.deepthought.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final ChatMemberInfoRepository chatMemberInfoRepository;
    private final ChatMapper chatMapper;
    private final UserMapper userMapper;

    @Transactional
    public ChatDto createChat(ChatCreationDto chatCreationDto){
        User user = userRepository.findByEmail(chatCreationDto.getCreator()).orElse(null);
        if (user == null) {
            throw new EntityExistsException("Пользователь c таким емаилом не существует ");
        }
        if (chatRepository.findByTitle(chatCreationDto.getChat()).orElse(null) != null) {
            throw new EntityExistsException("Такой чат уже существует невохможно создать ");
        }

        Chat chat = Chat.builder()
                    .title(chatCreationDto.getChat())
                    .build();
        ChatMemberInfo chatMemberInfo = ChatMemberInfo.builder()
                                        .chat(chat)
                                        .user(user)
                                        .build();
        
        chat = chatRepository.save(chat);
        chatMemberInfo = chatMemberInfoRepository.save(chatMemberInfo);

        return chatMapper.toDTO(chat);
        
    }

    public List<ChatDto> getAllUserChats(String email){
        List<ChatMemberInfo> chatMemberInfos = chatMemberInfoRepository.findAllByUserEmail(email);
        return chatMemberInfos.stream()
                            .map(ChatMemberInfo::getChat)
                            .map(chatMapper::toDTO)
                            .collect(Collectors.toList());
    }

    public List<UserDTO> getAllChatMembers(String title){
        List<ChatMemberInfo> chatMemberInfos = chatMemberInfoRepository.findAllByChatTitle(title);
        return chatMemberInfos.stream()
                            .map(ChatMemberInfo::getUser)
                            .map(userMapper::toDTO)
                            .collect(Collectors.toList());
    }

    public Integer getAllChatMembersCount(String title){
        List<ChatMemberInfo> chatMemberInfos = chatMemberInfoRepository.findAllByChatTitle(title);
        return chatMemberInfos.stream()
                            .map(ChatMemberInfo::getUser)
                            .map(userMapper::toDTO)
                            .collect(Collectors.toList()).size();
    }


    
}
