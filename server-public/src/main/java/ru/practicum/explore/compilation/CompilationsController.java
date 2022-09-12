package ru.practicum.explore.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.compilation.dto.CompilationDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationsController {

    private final CompilationsService compilationsService;

    @GetMapping
    public List<CompilationDto> getCompilations(
            @RequestParam(required = false) Boolean pinned,
            @RequestParam int from,
            @RequestParam int size
    ) {
        log.info("Get compilations pinned={}, from={}, size={}", pinned, from, size);
        return compilationsService.getCompilations(pinned, from, size);
    }

    //Получений подборки событий по id
    @GetMapping("/{compId}")
    public CompilationDto getCompilation(
            @PathVariable long compId
    ) {
        log.info("Get compilation id={}", compId);
        return compilationsService.getCompilation(compId);
    }

}
