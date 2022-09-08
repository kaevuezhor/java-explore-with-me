package ru.practicum.explore.category.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.service.AdminCategoryService;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {
    @Override
    public CategoryDto patchCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(long catId) {

    }
}
