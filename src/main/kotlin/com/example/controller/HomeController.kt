package com.example.controller

import com.example.service.DepartmentService
import com.example.service.EmployeeService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    private val employeeService: EmployeeService,
    private val departmentService: DepartmentService
) {

    /**
     * ホーム画面
     */
    @GetMapping("/")
    fun index(
        authentication: Authentication,
        model: Model
    ): String {

    
        model.addAttribute(
            "employeeCount",
            employeeService.countEmployees()
        )

        model.addAttribute(
            "departmentCount",
            departmentService.countDepartments()
        )

        model.addAttribute(
            "userId",
            authentication.name
        )

        return "home/index"
    }
}
