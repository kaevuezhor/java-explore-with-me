package ru.practicum.explore.category;

import org.springframework.stereotype.Component;
import ru.practicum.explore.category.model.Category;

@Component
public class CategoryMapper {

    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
