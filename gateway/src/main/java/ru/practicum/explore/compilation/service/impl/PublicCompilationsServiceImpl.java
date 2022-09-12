package ru.practicum.explore.compilation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.service.PublicCompilationsService;

import java.util.List;

@Service
public class PublicCompilationsServiceImpl implements PublicCompilationsService {

    private final WebClient webClient;

    private static final String API_PREFIX = "/compilations";

    @Autowired
    public PublicCompilationsServiceImpl(@Value("${explore-server-public.url}") String serverUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(serverUrl + API_PREFIX)
                .build();
    }
    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, int from, int size) {
        return webClient.get()
                .uri(
                        uriBuilder -> uriBuilder
                                .queryParam("pinned", pinned)
                                .queryParam("from", from)
                                .queryParam("size", size)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CompilationDto>>() {})
                .block();
    }

    @Override
    public CompilationDto getCompilation(long compId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{compId}")
                        .build(compId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CompilationDto.class)
                .block();
    }
}
