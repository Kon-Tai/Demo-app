package com.example.employeemanagement.controller;

import com.example.employeemanagement.entity.SystemSetting;
import com.example.employeemanagement.service.SystemSettingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final SystemSettingService systemSettingService;

    public SettingsController(
            SystemSettingService systemSettingService) {
        this.systemSettingService = systemSettingService;
    }


    /**
     * システム設定画面
     */
    @GetMapping
    public String settings(Model model) {

        SystemSetting settings =
                systemSettingService.getSettings();

        model.addAttribute("settings", settings);
        model.addAttribute("currentPage", "settings");

        return "setting/index";
    }


    /**
     * システム設定保存
     */
    @PostMapping
    public String saveSettings(
            @ModelAttribute SystemSetting settings) {

        systemSettingService.save(settings);

        return "redirect:/setting/index?saved=true";
    }
}
