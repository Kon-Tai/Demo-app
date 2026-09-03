package com.example.repository

import com.example.entity.SystemSetting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemSettingRepository : JpaRepository<SystemSetting, Long> {

    fun findFirstByOrderByIdAsc(): SystemSetting?
}
