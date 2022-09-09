package ru.practicum.explore.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.practicum.explore.category.model.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
