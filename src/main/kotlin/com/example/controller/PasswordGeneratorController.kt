package com.example.controller

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PasswordGeneratorController(
    private val passwordEncoder: PasswordEncoder
) {

    @GetMapping("/generate-password")
    fun generatePassword(): String? {
        return passwordEncoder.encode("Test1234")
    }
}