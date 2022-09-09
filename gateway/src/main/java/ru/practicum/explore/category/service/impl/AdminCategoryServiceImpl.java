package ru.practicum.explore.category.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.service.AdminCategoryService;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final WebClient webClient;

    private static final String API_PREFIX = "/categories";

    @Autowired
    public AdminCategoryServiceImpl(@Value("${explore-server-admin.url}") String serverUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(serverUrl + API_PREFIX)
                .build();
    }
    @Override
    public CategoryDto patchCategory(CategoryDto categoryDto) {
        return webClient.patch()
                .bodyValue(categoryDto)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();
    }

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) {
        return webClient.post()
                .bodyValue(categoryDto)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CategoryDto.class)
                .block();
    }

    @Override
    public void deleteCategory(long catId) {
        webClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/{catId}").build(catId))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
