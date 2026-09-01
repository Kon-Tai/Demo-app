package com.example.controller

import com.example.service.DepartmentService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.bind.annotation.PathVariable

@Controller
class DepartmentController(
    private val departmentService: DepartmentService
) {

    /**
     * 部署一覧
     */
    @GetMapping("/departments")
    fun index(
        authentication: Authentication,
        model: Model,
        @RequestParam(required = false) keyword: String?
    ): String {

        val departments =
            if (keyword.isNullOrBlank()) {
                departmentService.findAll()
            } else {
                departmentService.search(keyword)
            }

        model.addAttribute("departments", departments)
        model.addAttribute("userId", authentication.name)
        model.addAttribute("keyword", keyword ?: "")

        return "department/index"
    }

    /**
     * 部署新規登録画面
     */
    @GetMapping("/departments/new")
    fun newDepartment(
        authentication: Authentication,
        model: Model
    ): String {

        model.addAttribute("userId", authentication.name)

        return "department/new"
    }

    /**
     * 部署新規登録
     */
    @PostMapping("/departments")
    fun createDepartment(
        @RequestParam name: String,
        @RequestParam(required = false) code: String?,
        redirectAttributes: RedirectAttributes
    ): String {

        val trimmedName = name.trim()
        val trimmedCode = code?.trim()?.takeIf { it.isNotEmpty() }

        // 部署名チェック
        if (trimmedName.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "部署名を入力してください。"
            )

            return "redirect:/departments/new"
        }

        // 部署名重複チェック
        if (departmentService.existsByName(trimmedName)) {
            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "この部署名はすでに登録されています。"
            )

            return "redirect:/departments/new"
        }

        // 部署コード重複チェック
        if (trimmedCode != null &&
            departmentService.existsByCode(trimmedCode)
        ) {
            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "この部署コードはすでに登録されています。"
            )

            return "redirect:/departments/new"
        }

        departmentService.create(
            trimmedName,
            trimmedCode
        )

        redirectAttributes.addFlashAttribute(
            "successMessage",
            "部署を登録しました。"
        )

        return "redirect:/departments"
    }

    /**
 * 部署編集画面
 */
@GetMapping("/departments/{id}/edit")
fun editDepartment(
    authentication: Authentication,
    @PathVariable id: Long,
    model: Model
): String {

    val department = departmentService.findById(id)
        ?: return "redirect:/departments"

    model.addAttribute("department", department)
    model.addAttribute("userId", authentication.name)

    return "department/edit"
}

/**
 * 部署更新
 */
@PostMapping("/departments/{id}/update")
fun updateDepartment(
    @PathVariable id: Long,
    @RequestParam name: String,
    @RequestParam(required = false) code: String?,
    redirectAttributes: RedirectAttributes
): String {

    val department = departmentService.findById(id)

    if (department == null) {
        return "redirect:/departments"
    }

    val trimmedName = name.trim()
    val trimmedCode = code?.trim()?.takeIf { it.isNotEmpty() }

    // 部署名チェック
    if (trimmedName.isEmpty()) {
        redirectAttributes.addFlashAttribute(
            "errorMessage",
            "部署名を入力してください。"
        )

        return "redirect:/departments/$id/edit"
    }

    // 自分自身以外で同じ部署名が存在するかチェック
    if (departmentService.existsByNameExceptId(trimmedName, id)) {
        redirectAttributes.addFlashAttribute(
            "errorMessage",
            "この部署名はすでに登録されています。"
        )

        return "redirect:/departments/$id/edit"
    }

    // 自分自身以外で同じ部署コードが存在するかチェック
    if (
        trimmedCode != null &&
        departmentService.existsByCodeExceptId(trimmedCode, id)
    ) {
        redirectAttributes.addFlashAttribute(
            "errorMessage",
            "この部署コードはすでに登録されています。"
        )

        return "redirect:/departments/$id/edit"
    }

    departmentService.update(
        id,
        trimmedName,
        trimmedCode
    )

    redirectAttributes.addFlashAttribute(
        "successMessage",
        "部署情報を更新しました。"
    )

    return "redirect:/departments"
}
/**
 * 部署削除
 */
@PostMapping("/departments/{id}/delete")
fun deleteDepartment(
    @PathVariable id: Long,
    redirectAttributes: RedirectAttributes
): String {

    val deleted = departmentService.delete(id)

    if (deleted) {
        redirectAttributes.addFlashAttribute(
            "successMessage",
            "部署を削除しました。"
        )
    } else {
        redirectAttributes.addFlashAttribute(
            "errorMessage",
            "部署を削除できません。所属している社員がいる場合は、先に社員の所属部署を変更してください。"
        )
    }

    return "redirect:/departments"
}

}
