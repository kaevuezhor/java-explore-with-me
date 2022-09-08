package ru.practicum.explore.categories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
public class PublicCategoriesController {

    @GetMapping
    public List<CategoryDto> getCategories(
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("Get categories from={}, size={}", from, size);
        return null;
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategory(
            @PathVariable long catId
    ) {
        log.info("Get category catId={}", catId);
        return null;
    }
}
