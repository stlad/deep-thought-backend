package ru.rtf.rupp.deepthought.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated(forRemoval = true)
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseEntity<String> get(){
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }
}
