package ru.rtf.rupp.deepthought.service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import ru.rtf.rupp.deepthought.dto.MessageDto;
import ru.rtf.rupp.deepthought.entity.User;
import ru.rtf.rupp.deepthought.entity.Chat;
import ru.rtf.rupp.deepthought.entity.Message;
import ru.rtf.rupp.deepthought.repository.ChatRepository;
import ru.rtf.rupp.deepthought.repository.MessageRepository;
import ru.rtf.rupp.deepthought.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public MessageDto saveMessage(MessageDto dto) {

        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
        if (user == null){
            throw new EntityExistsException("Пользователь не существует ");
        }

        Chat chat = chatRepository.findById(dto.getChat()).orElse(null);
        if (chat == null){
            throw new EntityExistsException("Чат не существует ");
        }

        Message message = Message.builder()
                .user(user)
                .chat(chat)
                .content(dto.getText())
                .build();
        messageRepository.save(message);
        return dto;
    }

    public List<MessageDto> getMessagesFromChat(UUID chatId) {

        Chat chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null){
            throw new EntityExistsException("Чат не существует ");
        }

        List<Message>  messages = messageRepository.findAllMessagesByChatId(chatId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
    }

    private MessageDto convertToDto(Message message) {
        MessageDto dto =  MessageDto.builder()
                        .id(message.getId())
                        .text(message.getContent())
                        .email(message.getUser().getEmail())
                        .chat(message.getChat().getId())
                        .build();
        return dto;
    }
}