package ru.practicum.explore.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.dto.CategoryDto;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //Изменение категории
    @PatchMapping
    public CategoryDto patchCategory(
            @RequestBody CategoryDto categoryDto
    ) {
        log.info("Patch category patch={}", categoryDto);
        return categoryService.patchCategory(categoryDto);
    }

    //Добавление новой категории
    @PostMapping
    public CategoryDto postCategory(
            @RequestBody CategoryDto categoryDto
    ) {
        log.info("Post category category={}", categoryDto);
        return categoryService.postCategory(categoryDto);
    }

    //Удаление категории
    @DeleteMapping("/{catId}")
    public void deleteCategory(
            @PathVariable long catId
    ) {
        log.info("Delete category id={}", catId);
        categoryService.deleteCategory(catId);
    }
}
