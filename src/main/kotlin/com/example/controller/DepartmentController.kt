package com.example.controller

import com.example.service.DepartmentService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class DepartmentController(
    private val departmentService: DepartmentService
) {

    /**
     * 部署一覧
     */
    @GetMapping("/departments")
    fun index(
        authentication: Authentication,
        model: Model,
        @RequestParam(required = false) keyword: String?
    ): String {

        val departments =
            if (keyword.isNullOrBlank()) {
                departmentService.findAll()
            } else {
                departmentService.search(keyword)
            }

        model.addAttribute("departments", departments)
        model.addAttribute("userId", authentication.name)
        model.addAttribute("keyword", keyword ?: "")

        return "department/index"
    }

    /**
     * 部署新規登録画面
     */
    @GetMapping("/departments/new")
    fun newDepartment(
        authentication: Authentication,
        model: Model
    ): String {

        model.addAttribute("userId", authentication.name)

        return "department/new"
    }
}