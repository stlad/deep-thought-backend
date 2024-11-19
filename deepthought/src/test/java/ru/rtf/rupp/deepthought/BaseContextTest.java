package ru.rtf.rupp.deepthought;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rtf.rupp.deepthought.repository.UserProfileRepository;
import ru.rtf.rupp.deepthought.repository.UserRepository;
import ru.rtf.rupp.deepthought.repository.MessageRepository;
import ru.rtf.rupp.deepthought.repository.ChatMemberInfoRepository;
import ru.rtf.rupp.deepthought.repository.ChatRepository;

@SpringBootTest
public class BaseContextTest {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatMemberInfoRepository chatMemberInfoRepository;

    @Autowired
    private ChatRepository chatRepository;

}
