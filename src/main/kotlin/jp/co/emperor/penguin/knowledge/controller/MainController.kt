package jp.co.emperor.penguin.knowledge.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping( "/")
class MainController {

    @GetMapping("hello")
    fun get(): ResponseEntity<String> {
        return ResponseEntity<String>("Hello, World", HttpStatus.OK)
    }
}