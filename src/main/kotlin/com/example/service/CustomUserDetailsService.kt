package com.example.service

import com.example.repository.EmployeeRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val employeeRepository: EmployeeRepository
) : UserDetailsService {

    /**
     * 社員IDからログインユーザーを取得
     */
    override fun loadUserByUsername(
        username: String
    ): UserDetails {

        val employee =
            employeeRepository.findByEmployeeId(username)
                ?: throw UsernameNotFoundException(
                    "社員が見つかりません: $username"
                )

        return User.builder()
            .username(employee.employeeId)
            .password(employee.password)
            .roles("USER")
            .build()
    }
}