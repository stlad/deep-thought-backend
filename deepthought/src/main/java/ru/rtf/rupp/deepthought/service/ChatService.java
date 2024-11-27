package ru.rtf.rupp.deepthought.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.rtf.rupp.deepthought.repository.ChatMemberInfoRepository;
import ru.rtf.rupp.deepthought.repository.ChatRepository;
import ru.rtf.rupp.deepthought.repository.UserRepository;
import ru.rtf.rupp.deepthought.dto.ChatCreationDTO;
import ru.rtf.rupp.deepthought.dto.ChatMemberInfoDTO;
import ru.rtf.rupp.deepthought.dto.ChatDTO;
import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.entity.Chat;
import ru.rtf.rupp.deepthought.entity.ChatMemberInfo;
import ru.rtf.rupp.deepthought.entity.User;
import ru.rtf.rupp.deepthought.mapper.ChatMapper;
import ru.rtf.rupp.deepthought.mapper.UserMapper;
import ru.rtf.rupp.deepthought.enums.ChatRole;

import java.util.List;
import java.util.UUID;
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
    public ChatDTO createChat(ChatCreationDTO chatCreationDto){
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
        chatMemberInfo.setChatRole(ChatRole.CHAT_ADMIN);
        chat = chatRepository.save(chat);
        chatMemberInfo = chatMemberInfoRepository.save(chatMemberInfo);

        return chatMapper.toDTO(chat);
        
    }

    public List<ChatDTO> getAllUserChats(String email){
        List<ChatMemberInfo> chatMemberInfos = chatMemberInfoRepository.findAllByUserEmail(email);
        return chatMemberInfos.stream()
                            .map(ChatMemberInfo::getChat)
                            .map(chatMapper::toDTO)
                            .collect(Collectors.toList());
    }

    public List<UserDTO> getAllChatMembers(UUID id){
        List<ChatMemberInfo> chatMemberInfos = chatMemberInfoRepository.findAllByChatId(id);
        return chatMemberInfos.stream()
                            .map(ChatMemberInfo::getUser)
                            .map(userMapper::toDTO)
                            .collect(Collectors.toList());
    }

    public Integer getAllChatMembersCount(UUID id){
        List<ChatMemberInfo> chatMemberInfos = chatMemberInfoRepository.findAllByChatId(id);
        return chatMemberInfos.stream()
                            .map(ChatMemberInfo::getUser)
                            .map(userMapper::toDTO)
                            .collect(Collectors.toList()).size();
    }

    @Transactional
    public ChatMemberInfoDTO addUser(String email, UUID chatId){
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new EntityExistsException("Пользователь c таким емаилом не существует ");
        }
        Chat chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) {
            throw new EntityExistsException("Такой чат не существует невозсожно доабвить пользователя ");
        }

        List<ChatMemberInfo> chatMemberInfos = chatMemberInfoRepository.findByChatIdAndUser(chatId, email);
        System.out.println(chatMemberInfos);
        if (chatMemberInfos.isEmpty()){
            ChatMemberInfo chatMemberInfo = ChatMemberInfo
                                            .builder()
                                            .chat(chat)
                                            .user(user)
                                            .build();
            chatMemberInfoRepository.save(chatMemberInfo);
            return chatMapper.toDTO(chatMemberInfo);
        }
        throw new EntityExistsException("Пользователь уже добавлен в данный чат ");
    }

    @Transactional
    public Boolean removeUser(String email, UUID chatId){ // only chatAdmin can remove user todo add another DTO remake it
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new EntityExistsException("Пользователь c таким емаилом не существует ");
        }
        Chat chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) {
            throw new EntityExistsException("Такой чат не существует невозсожно доабвить пользователя ");
        }

        List<ChatMemberInfo> chatMemberInfos = chatMemberInfoRepository.findByChatIdAndUser(chatId, email);
        System.out.println(chatMemberInfos);
        if (!chatMemberInfos.isEmpty()){

            ChatMemberInfo chatMemberInfo = chatMemberInfos.get(0);
            chatMemberInfoRepository.delete(chatMemberInfo);
            return true;
        }
        throw new EntityExistsException("Такого пользователя в чате нет");
    }

}
