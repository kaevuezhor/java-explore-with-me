package ru.practicum.explore.category.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.service.PublicCategoriesService;

import java.util.List;

@Service
public class PublicCategoryServiceImpl implements PublicCategoriesService {
    @Override
    public List<CategoryDto> getCategories(int from, int size) {
        return null;
    }

    @Override
    public CategoryDto getCategory(long id) {
        return null;
    }
}
