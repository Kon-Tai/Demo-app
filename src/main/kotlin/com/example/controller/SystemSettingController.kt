package com.example.controller

import com.example.entity.SystemSetting
import com.example.service.SystemSettingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class SystemSettingController(
    private val systemSettingService: SystemSettingService
) {

    @GetMapping("/settings")
    fun index(model: Model): String {

        val settings = systemSettingService.getSettings()

        model.addAttribute("settings", settings)
        model.addAttribute("currentPage", "settings")

        return "setting/index"
    }

    @PostMapping("/settings")
    fun update(
        @ModelAttribute("settings") settings: SystemSetting
    ): String {

        systemSettingService.updateSettings(settings)

      return "redirect:/settings"
    }
}