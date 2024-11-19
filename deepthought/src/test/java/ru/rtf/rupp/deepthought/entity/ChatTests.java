package ru.rtf.rupp.deepthought.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.rtf.rupp.deepthought.BaseContextTest;

class ChatTests extends BaseContextTest {

    @Test
    @DisplayName("Инит всех полей")
    void chatEntityDefaultFileds_init() {
        Chat chat = Chat.builder()
                .title("Some title")
                .build();

        Assertions.assertNotNull(chat.getTitle());
        Assertions.assertNotNull(chat.getCreated_at());
    }

    
}