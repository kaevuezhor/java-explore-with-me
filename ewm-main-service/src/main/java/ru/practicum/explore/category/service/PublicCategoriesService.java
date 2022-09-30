package ru.practicum.explore.category.service;

import ru.practicum.explore.category.dto.CategoryDto;

import java.util.List;

public interface PublicCategoriesService {

    List<CategoryDto> getCategories(int from, int size);

    CategoryDto getCategory(long id) ;
}
