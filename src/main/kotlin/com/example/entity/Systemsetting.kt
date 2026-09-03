package com.example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "system_settings")
class SystemSetting(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "system_name", nullable = false)
    var systemName: String = "社員管理システム",

    @Column(name = "company_name", nullable = false)
    var companyName: String = "株式会社サンプル",

    @Column(name = "timezone", nullable = false)
    var timezone: String = "Asia/Tokyo",

    @Column(name = "employee_id_prefix", nullable = false)
    var employeeIdPrefix: String = "EMP",

    @Column(name = "email_required", nullable = false)
    var emailRequired: Boolean = true,

    @Column(name = "show_retired_employees", nullable = false)
    var showRetiredEmployees: Boolean = false,

    @Column(name = "mail_notification", nullable = false)
    var mailNotification: Boolean = true,

    @Column(name = "employee_registration_notification", nullable = false)
    var employeeRegistrationNotification: Boolean = true,

    @Column(name = "session_timeout", nullable = false)
    var sessionTimeout: Int = 30,

    @Column(name = "login_failure_limit", nullable = false)
    var loginFailureLimit: Int = 5
)