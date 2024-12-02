package ru.rtf.rupp.deepthought.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.rtf.rupp.deepthought.entity.Chat;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ChatRepositoryTests {

    @Autowired
    private ChatRepository chatRepository;

    private Chat chat;

    @BeforeEach
    public void setUp() {
        chat = Chat.builder()
                .title("Test Chat")
                .build();
        chatRepository.save(chat);
    }

    @Test
    public void testFindById() {
        Optional<Chat> foundChat = chatRepository.findById(chat.getId());
        assertThat(foundChat).isPresent();
        assertThat(foundChat.get().getTitle()).isEqualTo("Test Chat");
    }

    @Test
    public void testFindAll() {
        List<Chat> chats = chatRepository.findAll();
        assertThat(chats).isNotEmpty();
        assertThat(chats).contains(chat);
    }

    @Test
    public void testSave() {
        Chat newChat = Chat.builder()
                .title("New Chat")
                .build();
        Chat savedChat = chatRepository.save(newChat);
        assertThat(savedChat).isNotNull();
        assertThat(savedChat.getTitle()).isEqualTo("New Chat");
    }

    @Test
    public void testDelete() {
        chatRepository.delete(chat);
        Optional<Chat> deletedChat = chatRepository.findById(chat.getId());
        assertThat(deletedChat).isNotPresent();
    }
}