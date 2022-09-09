package ru.practicum.explore.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.mapper.CategoryMapper;
import ru.practicum.explore.category.model.Category;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto patchCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.getReferenceById(categoryDto.getId());
        savedCategory.setName(categoryDto.getName());
        return categoryMapper.toCategoryDto(categoryRepository.save(savedCategory));
    }

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(categoryMapper.toCategoryModel(categoryDto));
        return categoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public void deleteCategory(long catId) {
        categoryRepository.deleteById(catId);
    }
}
