package ru.practicum.explore.category;

import ru.practicum.explore.category.dto.CategoryDto;

public interface CategoryService {
    CategoryDto patchCategory(CategoryDto categoryDto);

    CategoryDto postCategory(CategoryDto categoryDto);

    void deleteCategory(long catId);
}
