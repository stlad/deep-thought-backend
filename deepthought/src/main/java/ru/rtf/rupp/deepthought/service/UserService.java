package ru.rtf.rupp.deepthought.service;

import org.hibernate.Hibernate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import ru.rtf.rupp.deepthought.entity.UserRole;
import ru.rtf.rupp.deepthought.enums.SystemRole;
import ru.rtf.rupp.deepthought.entity.UserProfile;
import ru.rtf.rupp.deepthought.mapper.UserMapper;
import ru.rtf.rupp.deepthought.repository.SystemRoleRepository;
import ru.rtf.rupp.deepthought.repository.UserProfileRepository;
import ru.rtf.rupp.deepthought.repository.UserRepository;

import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final SystemRoleRepository systemRoleRepository;

    @Transactional
    public UserDTO saveUser(UserRegistrationDTO dto) {
        if (userRepository.existsByLoginOrEmail(dto.getLogin(), dto.getEmail())) {
            throw new EntityExistsException("Пользователь таким логином или почтой уже зарегистрирован ");
        }
        User user = User.builder()
                .email(dto.getEmail())
                .login(dto.getLogin())
                .password(passwordEncoder.encode(dto.getPassword()))
                .systemRoles(Set.of(UserRole.builder().role(SystemRole.USER).build()))
                .build();
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO findUser(String login, String email) {
        if (login == null && email == null) {
            throw new BadCredentialsException("Не указаны логин и пароль");
        }
        User user;
        if (login != null) {
            user = userRepository.findByLogin(login)
                    .orElseThrow(() -> new BadCredentialsException("Пользователь таким логином не найден"));
        } else {
            user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new BadCredentialsException("Пользователь такой почтой не найден"));
        }
        return userMapper.toDTO(user);
    }

    public Authentication login(UserRegistrationDTO dto) {
        if (dto.getLogin() == null && dto.getEmail() == null) {
            throw new BadCredentialsException("Не указаны логин и пароль");
        }
        UserDetails user;
        if (dto.getLogin() != null) {
            user = loadUserByUsername(dto.getLogin());
        } else {
            user = loadUserByUsername(dto.getEmail());
        }

        Objects.requireNonNull(dto.getPassword(), "Пароль не должен быть пустым полем");
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Неверный пароль");
        }

        return new UsernamePasswordAuthenticationToken(userMapper.toDTO((User) user), dto.getPassword(), user.getAuthorities());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user =  userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Hibernate.initialize(user.getAuthorities());
        return user;
    }

    public UserDTO getUserByEmail(String userID) {
        User user = userRepository.findByEmail(userID).orElse(null);
        if (user == null) {
            throw new EntityExistsException("Такого пользователя нет");
        }
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO grantRole(String login, SystemRole role) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new BadCredentialsException("Пользователь таким логином не найден"));
        if (systemRoleRepository.existsByUser_LoginAndRole(login, role)) {
            return userMapper.toDTO(user);
        }

        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO updateProfile(UserDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
        if (user == null) {
            throw new EntityExistsException("Пользователь не существует");
        }
        UserProfile updatedProfile = userMapper.toEntity(dto.getProfile());
        user.setProfile(updatedProfile);
        userProfileRepository.save(updatedProfile);
        userRepository.save(user);

        return userMapper.toDTO(user);
    }

}
