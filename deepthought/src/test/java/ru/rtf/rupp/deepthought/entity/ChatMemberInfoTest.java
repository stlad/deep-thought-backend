package ru.rtf.rupp.deepthought.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.rtf.rupp.deepthought.BaseContextTest;

public class ChatMemberInfoTest extends BaseContextTest {
    
    @Test
    @DisplayName("Инит всех полей")
    void chatMemberInfoEntityDefaultFields_init(){
        User user = User.builder()
                .login("test")
                .email("test@test.com")
                .password("test")
                .build();

        Chat chat = Chat.builder()
            .title("Some title")
            .build();

        ChatMemberInfo chatMemberInfo = ChatMemberInfo.builder()
                                        .chat(chat)
                                        .user(user)
                                        .build();
        Assertions.assertNotNull(chatMemberInfo.getUser());
        Assertions.assertNotNull(chatMemberInfo.getJoined_at());
        Assertions.assertNotNull(chatMemberInfo.getChat());
        Assertions.assertEquals(chatMemberInfo.getChat_role(), ChatRole.USER);

        }
}
