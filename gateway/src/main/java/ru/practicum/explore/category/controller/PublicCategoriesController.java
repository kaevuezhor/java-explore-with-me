package ru.practicum.explore.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.service.PublicCategoriesService;
import ru.practicum.explore.category.dto.CategoryDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class PublicCategoriesController {

    private final PublicCategoriesService publicCategoriesService;

    //Получение категорий
    @GetMapping
    public List<CategoryDto> getCategories(
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("Get categories from={}, size={}", from, size);
        return publicCategoriesService.getCategories(from, size);
    }

    //Получение информации о категории по идентификатору
    @GetMapping("/{catId}")
    public CategoryDto getCategory(
            @PathVariable long catId
    ) {
        log.info("Get category catId={}", catId);
        return publicCategoriesService.getCategory(catId);
    }
}
