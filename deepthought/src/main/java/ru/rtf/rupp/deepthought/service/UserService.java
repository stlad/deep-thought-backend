package ru.rtf.rupp.deepthought.service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.dto.UserRegistrationDTO;
import ru.rtf.rupp.deepthought.entity.User;
import ru.rtf.rupp.deepthought.mapper.UserMapper;
import ru.rtf.rupp.deepthought.repository.UserRepository;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public UserDTO saveUser(UserRegistrationDTO dto) {
        if (userRepository.existsByLoginOrEmail(dto.getLogin(), dto.getEmail())) {
            throw new EntityExistsException("Пользователь таким логином или почтой уже зарегистрирован ");
        }
        User user = User.builder()
                .email(dto.getEmail())
                .login(dto.getLogin())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO login(UserRegistrationDTO dto) {
        if (dto.getLogin() == null && dto.getEmail() == null) {
            throw new BadCredentialsException("Не указаны логин и пароль");
        }
        User user;
        if (dto.getLogin() != null) {
            user = userRepository.findByLogin(dto.getLogin())
                    .orElseThrow(() -> new BadCredentialsException("Пользователь таким логином не найден"));
        } else {
            user = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Пользователь такой почтой не найден"));
        }

        Objects.requireNonNull(dto.getPassword(), "Пароль не должен быть пустым полем");
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Неверный пароль");
        }
        return userMapper.toDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDTO getUserByEmail(String userID) {
        User user = userRepository.findByEmail(userID).orElse(null);
        if (user == null){
            throw new EntityExistsException("Такого пользователя нет");
        }
        return userMapper.toDTO(user);
    }
}
