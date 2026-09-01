package com.example.repository

import com.example.entity.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentRepository : JpaRepository<Department, Long> {

    /**
     * 部署名検索
     */
    fun findByNameContainingIgnoreCase(
        keyword: String
    ): List<Department>

    /**
     * 部署コード検索
     */
    fun findByCode(code: String): Department?

    /**
     * 部署名存在チェック
     */
    fun existsByName(name: String): Boolean

    /**
     * 部署名存在チェック（指定IDを除外）
     */
    fun existsByNameAndIdNot(
        name: String,
        id: Long
    ): Boolean

    /**
     * 部署コード存在チェック
     */
    fun existsByCode(code: String): Boolean

    /**
     * 部署コード存在チェック（指定IDを除外）
     */
    fun existsByCodeAndIdNot(
        code: String,
        id: Long
    ): Boolean
}
