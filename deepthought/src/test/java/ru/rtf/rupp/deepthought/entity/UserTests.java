package ru.rtf.rupp.deepthought.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.rtf.rupp.deepthought.BaseContextTest;
import ru.rtf.rupp.deepthought.repository.UserInfoRepostory;
import ru.rtf.rupp.deepthought.repository.UserRepository;

class UserTests extends BaseContextTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepostory userInfoRepostory;

    @Test
    void test() {
        var u = User.builder()
                .login("hello")
                .password("world")
                .build();

       u = userRepository.save(u);
       var d = u.getInfo();
       userRepository.delete(u);
        Assertions.assertNotNull(u.getInfo());
    }
}