package jp.co.emperor.penguin.knowledge.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping( "/")
class MainController {

    @GetMapping
    fun get(
        @RequestParam(name = "isSc4xx", defaultValue = "false", required = false) isSc4xx: Boolean
    ): ResponseEntity<String> {
        val httpStatus = if (isSc4xx) HttpStatus.BAD_REQUEST else HttpStatus.OK
        return ResponseEntity<String>("Hello, World", httpStatus)
    }
}