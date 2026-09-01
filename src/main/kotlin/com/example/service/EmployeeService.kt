package com.example.service

import com.example.entity.Employee
import com.example.repository.DepartmentRepository
import com.example.repository.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val departmentRepository: DepartmentRepository
) {

    /**
     * 社員を全件取得
     */
    fun findAll(): List<Employee> {
        return employeeRepository.findAll()
    }

    /**
     * 社員数を取得
     */
    fun countEmployees(): Long {
        return employeeRepository.count()
    }

    /**
     * 社員を検索
     */
    fun search(keyword: String): List<Employee> {
        return employeeRepository.findByNameContaining(keyword)
    }

    /**
     * IDから社員を取得
     */
    fun findById(id: Long): Employee? {
        return employeeRepository.findById(id).orElse(null)
    }

    /**
     * 社員IDから社員を取得
     */
    fun findByEmployeeId(employeeId: String): Employee? {
        return employeeRepository.findByEmployeeId(employeeId)
    }

    /**
     * 社員情報を更新
     */
    @Transactional
fun update(
    id: Long,
    name: String,
    departmentId: Long?,
    position: String?,
    email: String?
): Employee? {

    val employee =
        employeeRepository.findById(id).orElse(null)
            ?: return null

    val department =
        departmentId?.let {
            departmentRepository.findById(it).orElse(null)
        }

    employee.name = name
    employee.department = department
    employee.position = position
    employee.email = email

    return employeeRepository.save(employee)
}

    /**
     * 社員を新規登録
     */
    @Transactional
    fun create(
        employeeId: String,
        name: String,
        departmentId: Long?,
        position: String?,
        email: String?
    ): Employee {

        val department =
            departmentId?.let {
                departmentRepository.findById(it).orElse(null)
            }

        val employee = Employee().apply {
            this.employeeId = employeeId
            this.name = name
            this.department = department
            this.position = position
            this.email = email
        }

        return employeeRepository.save(employee)
    }
}
