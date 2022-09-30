package ru.practicum.explore.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.service.AdminCategoryService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Validated
public class AdminCategoriesController {

    private final AdminCategoryService adminCategoryService;

    //Изменение категории
    @PatchMapping
    public CategoryDto patchCategory(
            @RequestBody @Valid CategoryDto categoryDto
    ) {
        log.info("[ADMIN] Patch category patch={}", categoryDto);
        return adminCategoryService.patchCategory(categoryDto);
    }

    //Добавление новой категории
    @PostMapping
    public CategoryDto postCategory(
            @RequestBody @Valid CategoryDto categoryDto
    ) {
        log.info("[ADMIN] Post category category={}", categoryDto);
        return adminCategoryService.postCategory(categoryDto);
    }

    //Удаление категории
    @DeleteMapping("/{catId}")
    public void deleteCategory(
            @PathVariable long catId
    ) {
        log.info("[ADMIN] Delete category id={}", catId);
        adminCategoryService.deleteCategory(catId);
    }
}
