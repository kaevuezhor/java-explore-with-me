package ru.practicum.explore.category.service;

import ru.practicum.explore.category.dto.CategoryDto;

public interface AdminCategoryService {

    CategoryDto patchCategory(CategoryDto categoryDto);

    CategoryDto postCategory(CategoryDto categoryDto);

    void deleteCategory(long catId);
}
