package com.example.service

import com.example.entity.Department
import com.example.repository.DepartmentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DepartmentService(
    private val departmentRepository: DepartmentRepository
) {

    /**
     * 部署一覧取得
     */
    fun findAll(): List<Department> {
        return departmentRepository.findAll()
            .sortedBy { it.id }
    }

    /**
     * 部署検索
     */
    fun search(keyword: String): List<Department> {
        return departmentRepository
            .findByNameContainingIgnoreCase(keyword)
            .sortedBy { it.id }
    }

    /**
     * 部署数取得
     */
    fun countDepartments(): Long {
        return departmentRepository.count()
    }

    /**
     * 部署取得
     */
    fun findById(id: Long): Department? {
        return departmentRepository
            .findById(id)
            .orElse(null)
    }

    /**
     * 部署新規登録
     */
    @Transactional
    fun create(
        name: String,
        code: String?
    ): Department {

        val department = Department()

        department.name = name
        department.code = code

        return departmentRepository.save(department)
    }

    /**
     * 部署更新
     */
    @Transactional
    fun update(
        id: Long,
        name: String,
        code: String?
    ): Department? {

        val department =
            departmentRepository.findById(id)
                .orElse(null)
                ?: return null

        department.name = name
        department.code = code

        return departmentRepository.save(department)
    }

    /**
     * 部署削除
     */
    @Transactional
    fun delete(id: Long): Boolean {

        val department =
            departmentRepository.findById(id)
                .orElse(null)
                ?: return false

        if (department.employees.isNotEmpty()) {
            return false
        }

        departmentRepository.delete(department)

        return true
    }
}