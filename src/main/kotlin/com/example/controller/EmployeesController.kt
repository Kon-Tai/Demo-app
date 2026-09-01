package com.example.controller

import com.example.service.DepartmentService
import com.example.service.EmployeeService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class EmployeeController(
    private val employeeService: EmployeeService,
    private val departmentService: DepartmentService
) {

    /**
     * 社員一覧
     */
    @GetMapping("/employees")
    fun index(
        authentication: Authentication,
        model: Model,
        @RequestParam(required = false) keyword: String?
    ): String {

        val employees =
            if (keyword.isNullOrBlank()) {
                employeeService.findAll()
            } else {
                employeeService.search(keyword)
            }

        model.addAttribute("employees", employees)
        model.addAttribute("userId", authentication.name)
        model.addAttribute("keyword", keyword ?: "")

        return "employee/index"
    }

    /**
     * 社員詳細
     */
    @GetMapping("/employees/{id}")
    fun show(
        @PathVariable id: Long,
        authentication: Authentication,
        model: Model
    ): String {

        val employee = employeeService.findById(id)

        if (employee == null) {
            return "redirect:/employees"
        }

        model.addAttribute("employee", employee)
        model.addAttribute("userId", authentication.name)

        return "employee/show"
    }

    /**
     * 社員編集画面
     */
    @GetMapping("/employees/{id}/edit")
    fun edit(
        @PathVariable id: Long,
        authentication: Authentication,
        model: Model
    ): String {

        val employee = employeeService.findById(id)

        if (employee == null) {
            return "redirect:/employees"
        }

        val departments = departmentService.findAll()

        model.addAttribute("employee", employee)
        model.addAttribute("departments", departments)
        model.addAttribute("userId", authentication.name)

        return "employee/edit"
    }

    /**
     * 社員情報更新
     */
    @PostMapping("/employees/{id}")
    fun update(
        @PathVariable id: Long,

        @RequestParam name: String,

        @RequestParam(required = false)
        departmentId: Long?,

        @RequestParam(required = false)
        position: String?,

        @RequestParam(required = false)
        email: String?
    ): String {

        employeeService.update(
            id = id,
            name = name,
            departmentId = departmentId,
            position = position,
            email = email
        )

        return "redirect:/employees/$id"
    }

    /**
     * 社員新規登録画面
     */
    @GetMapping("/employees/new")
    fun newEmployee(
        authentication: Authentication,
        model: Model
    ): String {

        val departments = departmentService.findAll()

        model.addAttribute("departments", departments)
        model.addAttribute("userId", authentication.name)

        return "employee/new"
    }

    /**
     * 社員新規登録
     */
    @PostMapping("/employees")
    fun create(
        @RequestParam employeeId: String,

        @RequestParam name: String,

        @RequestParam(required = false)
        departmentId: Long?,

        @RequestParam(required = false)
        position: String?,

        @RequestParam(required = false)
        email: String?
    ): String {

        val employee = employeeService.create(
            employeeId = employeeId,
            name = name,
            departmentId = departmentId,
            position = position,
            email = email
        )

        return "redirect:/employees/${employee.id}"
    }
}
