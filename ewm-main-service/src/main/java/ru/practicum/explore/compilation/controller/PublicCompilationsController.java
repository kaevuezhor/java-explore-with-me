package ru.practicum.explore.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.service.PublicCompilationsService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
public class PublicCompilationsController {

    private final PublicCompilationsService publicCompilationsService;

    //Получение подборок событий
    @GetMapping
    public List<CompilationDto> getCompilations(
            @RequestParam(required = false) Boolean pinned,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("Get compilations pinned={}, from={}, size={}", pinned, from, size);
        return publicCompilationsService.getCompilations(pinned, from, size);
    }

    //Получений подборки событий по id
    @GetMapping("/{compId}")
    public CompilationDto getCompilation(
            @PathVariable long compId
    ) {
        log.info("Get compilation id={}", compId);
        return publicCompilationsService.getCompilation(compId);
    }
}
