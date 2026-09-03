package com.example.repository

import com.example.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {

    /**
     * 社員IDから取得
     */
    fun findByEmployeeId(employeeId: String): Employee?

    /**
     * 氏名検索
     */
    fun findByNameContaining(name: String): List<Employee>

    /**
     * 指定した接頭辞の社員IDの最大値を取得
     *
     * 例：
     * EMP0001
     * EMP0002
     * EMP0005
     *
     * → EMP0005
     */
    fun findTopByEmployeeIdStartingWithOrderByEmployeeIdDesc(
        prefix: String
    ): Employee?

    /**
     * 社員検索
     *
     * 社員ID、氏名、部署名、役職、メールアドレスを検索
     */
    @Query(
        """
        SELECT e
        FROM Employee e
        WHERE e.employeeId LIKE %:keyword%
           OR e.name LIKE %:keyword%
           OR e.department.name LIKE %:keyword%
           OR e.position LIKE %:keyword%
           OR e.email LIKE %:keyword%
        """
    )
    fun search(
        @Param("keyword") keyword: String
    ): List<Employee>
}
