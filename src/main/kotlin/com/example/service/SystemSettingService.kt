package com.example.service

import com.example.entity.SystemSetting
import com.example.repository.SystemSettingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SystemSettingService(
    private val systemSettingRepository: SystemSettingRepository
) {

    /**
     * システム設定を取得する
     *
     * DBに設定が存在しない場合は初期設定を作成する
     */
    @Transactional
    fun getSettings(): SystemSetting {

        val settings = systemSettingRepository.findFirstByOrderByIdAsc()

        if (settings != null) {
            return settings
        }

        val defaultSettings = createDefaultSettings()

        return systemSettingRepository.save(defaultSettings)
    }

    /**
     * システム設定を更新する
     */
    @Transactional
    fun updateSettings(settings: SystemSetting): SystemSetting {

        val currentSettings = getSettings()

        currentSettings.systemName = settings.systemName
        currentSettings.companyName = settings.companyName
        currentSettings.timezone = settings.timezone
        currentSettings.employeeIdPrefix = settings.employeeIdPrefix
        currentSettings.emailRequired = settings.emailRequired
        currentSettings.showRetiredEmployees = settings.showRetiredEmployees
        currentSettings.mailNotification = settings.mailNotification
        currentSettings.employeeRegistrationNotification =
            settings.employeeRegistrationNotification
        currentSettings.sessionTimeout = settings.sessionTimeout
        currentSettings.loginFailureLimit = settings.loginFailureLimit

        return systemSettingRepository.save(currentSettings)
    }

    /**
     * 初期設定
     */
    private fun createDefaultSettings(): SystemSetting {

        return SystemSetting(
            systemName = "社員管理システム",
            companyName = "株式会社サンプル",
            timezone = "Asia/Tokyo",
            employeeIdPrefix = "EMP",
            emailRequired = true,
            showRetiredEmployees = false,
            mailNotification = true,
            employeeRegistrationNotification = true,
            sessionTimeout = 30,
            loginFailureLimit = 5
        )
    }
}
