package com.example.employeemanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "system_settings")
public class SystemSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * システム名
     */
    @Column(name = "system_name", nullable = false)
    private String systemName;

    /**
     * 会社名
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * タイムゾーン
     */
    @Column(name = "timezone", nullable = false)
    private String timezone;

    /**
     * 社員IDプレフィックス
     */
    @Column(name = "employee_id_prefix", nullable = false)
    private String employeeIdPrefix;

    /**
     * メールアドレスを必須にする
     */
    @Column(name = "email_required", nullable = false)
    private boolean emailRequired;

    /**
     * 退職社員を一覧に表示する
     */
    @Column(name = "show_retired_employees", nullable = false)
    private boolean showRetiredEmployees;

    /**
     * メール通知
     */
    @Column(name = "mail_notification", nullable = false)
    private boolean mailNotification;

    /**
     * 社員登録時の通知
     */
    @Column(name = "employee_registration_notification", nullable = false)
    private boolean employeeRegistrationNotification;

    /**
     * セッションタイムアウト（分）
     */
    @Column(name = "session_timeout", nullable = false)
    private Integer sessionTimeout;

    /**
     * ログイン失敗許容回数
     */
    @Column(name = "login_failure_limit", nullable = false)
    private Integer loginFailureLimit;


    public SystemSetting() {
    }


    public Long getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getEmployeeIdPrefix() {
        return employeeIdPrefix;
    }

    public void setEmployeeIdPrefix(String employeeIdPrefix) {
        this.employeeIdPrefix = employeeIdPrefix;
    }

    public boolean isEmailRequired() {
        return emailRequired;
    }

    public void setEmailRequired(boolean emailRequired) {
        this.emailRequired = emailRequired;
    }

    public boolean isShowRetiredEmployees() {
        return showRetiredEmployees;
    }

    public void setShowRetiredEmployees(boolean showRetiredEmployees) {
        this.showRetiredEmployees = showRetiredEmployees;
    }

    public boolean isMailNotification() {
        return mailNotification;
    }

    public void setMailNotification(boolean mailNotification) {
        this.mailNotification = mailNotification;
    }

    public boolean isEmployeeRegistrationNotification() {
        return employeeRegistrationNotification;
    }

    public void setEmployeeRegistrationNotification(
            boolean employeeRegistrationNotification) {
        this.employeeRegistrationNotification =
                employeeRegistrationNotification;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Integer getLoginFailureLimit() {
        return loginFailureLimit;
    }

    public void setLoginFailureLimit(Integer loginFailureLimit) {
        this.loginFailureLimit = loginFailureLimit;
    }
}