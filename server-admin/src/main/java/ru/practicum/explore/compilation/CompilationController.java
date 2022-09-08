package ru.practicum.explore.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;

@Slf4j
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationController {

    private final CompilationService compilationService;

    //Добавление новой подборки
    @PostMapping
    public CompilationDto postCompilation(
            @RequestBody NewCompilationDto newCompilationDto
    ) {
        log.info("Post compilation compilation={}", newCompilationDto);
        return compilationService.postCompilation(newCompilationDto);
    }

    //Удаление подборки
    @DeleteMapping("/{compId}")
    public void deleteCompilation(
            @PathVariable long compId
    ) {
        log.info("Delete compilation id={}", compId);
        compilationService.deleteCompilation(compId);
    }

    //Удаление события из подборки
    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(
            @PathVariable long compId,
            @PathVariable long eventId
    ) {
        log.info("Delete event id={} from compilation id={}", eventId, compId);
        compilationService.deleteEventFromCompilation(compId, eventId);
    }

    //Добавить событие в подборку
    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(
            @PathVariable long compId,
            @PathVariable long eventId
    ) {
        log.info("Add event id={} to compilation id={}", eventId, compId);
        compilationService.addEventToCompilation(compId, eventId);
    }

    //Открепить подборку на гланой странице
    @DeleteMapping("/{compId}/pin")
    public void removeCompilationPin(
            @PathVariable long compId
    ) {
        log.info("Remove compilation pin id={}", compId);
        compilationService.removeCompilationPin(compId);
    }

    //Закрепить подборку на главной странице
    @PatchMapping("/{compId}/pin")
    public void addCompilationPin(
            @PathVariable long compId
    ) {
        log.info("Add compilation pin id={}", compId);
        compilationService.addCompilationPin(compId);
    }
}
