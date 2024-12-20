package ru.rtf.rupp.deepthought.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.dto.UserRegistrationDTO;
import ru.rtf.rupp.deepthought.enums.SystemRole;
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
    public ResponseEntity<Authentication> login(@RequestBody UserRegistrationDTO dto){
        Authentication user = userService.login(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user")
    @Operation(summary = "Получение пользователя")
    public ResponseEntity<UserDTO> getUser(@RequestBody UserRegistrationDTO dto){
        UserDTO user = userService.findUser(dto.getLogin(), dto.getEmail());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/grant")
    @Operation(summary = "Получение пользователя")
    public ResponseEntity<UserDTO> login(@RequestParam String login, @RequestParam SystemRole role){
        UserDTO user = userService.grantRole(login, role);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
