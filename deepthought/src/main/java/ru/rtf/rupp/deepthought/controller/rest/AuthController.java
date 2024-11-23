package ru.rtf.rupp.deepthought.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.dto.UserRegistrationDTO;
import ru.rtf.rupp.deepthought.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Создание пользователя")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegistrationDTO dto){
        UserDTO user = userService.saveUser(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Получение пользователя")
    public ResponseEntity<UserDTO> login(@RequestBody UserRegistrationDTO dto){
        UserDTO user = userService.login(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
