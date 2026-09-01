package com.example.controller

import com.example.service.EmployeeService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ProfileController(
    private val employeeService: EmployeeService
) {

    /**
     * ユーザー詳細画面
     */
    @GetMapping("/profile")
    fun index(
        authentication: Authentication,
        model: Model
    ): String {

        // ログイン中のユーザーIDを取得
        val userId = authentication.name

        // ユーザーIDを社員IDとして社員情報を取得
        val employee =
            employeeService.findByEmployeeId(userId)

        model.addAttribute(
            "userId",
            userId
        )

        model.addAttribute(
            "employee",
            employee
        )

        return "profile/index"
    }
}