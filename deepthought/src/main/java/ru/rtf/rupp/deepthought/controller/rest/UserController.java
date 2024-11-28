package ru.rtf.rupp.deepthought.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.service.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/find")
    @Operation(summary = "Получение пользователя")
    public ResponseEntity<UserDTO> getUser(String email){
        UserDTO user = userService.getUserByEmail(email);
        System.out.println(user.getEmail());
        return ResponseEntity.ok(user);
    }

}