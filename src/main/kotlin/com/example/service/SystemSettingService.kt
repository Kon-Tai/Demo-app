package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.SystemSetting;
import com.example.employeemanagement.repository.SystemSettingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemSettingService {

    private final SystemSettingRepository systemSettingRepository;

    public SystemSettingService(
            SystemSettingRepository systemSettingRepository) {
        this.systemSettingRepository = systemSettingRepository;
    }


    /**
     * システム設定を取得する。
     *
     * 設定が存在しない場合はデフォルト設定を作成する。
     */
    @Transactional
    public SystemSetting getSettings() {

        return systemSettingRepository
                .findFirstByOrderByIdAsc()
                .orElseGet(this::createDefaultSettings);
    }


    /**
     * システム設定を保存する。
     */
    @Transactional
    public SystemSetting save(SystemSetting settings) {

        return systemSettingRepository.save(settings);
    }


    /**
     * 初期設定を作成する。
     */
    private SystemSetting createDefaultSettings() {

        SystemSetting settings = new SystemSetting();

        settings.setSystemName("社員管理システム");
        settings.setCompanyName("");
        settings.setTimezone("Asia/Tokyo");

        settings.setEmployeeIdPrefix("EMP");

        settings.setEmailRequired(true);
        settings.setShowRetiredEmployees(false);

        settings.setMailNotification(true);
        settings.setEmployeeRegistrationNotification(true);

        settings.setSessionTimeout(30);
        settings.setLoginFailureLimit(5);

        return systemSettingRepository.save(settings);
    }
}
