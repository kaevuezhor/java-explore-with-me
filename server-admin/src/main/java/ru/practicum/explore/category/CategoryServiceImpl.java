package ru.practicum.explore.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.dto.CategoryDto;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

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
