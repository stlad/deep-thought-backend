package ru.rtf.rupp.deepthought;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rtf.rupp.deepthought.repository.UserProfileRepository;
import ru.rtf.rupp.deepthought.repository.UserRepository;

@SpringBootTest
public class BaseContextTest {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;
}
