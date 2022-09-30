package ru.practicum.explore.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;
import ru.practicum.explore.compilation.service.AdminCompilationsService;

@Slf4j
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
public class AdminCompilationsController {

    private final AdminCompilationsService adminCompilationsService;

    //Добавление новой подборки
    @PostMapping
    public CompilationDto postCompilation(
            @RequestBody NewCompilationDto newCompilationDto
    ) {
        log.info("[ADMIN] Post compilation compilation={}", newCompilationDto);
        return adminCompilationsService.postCompilation(newCompilationDto);
    }

    //Удаление подборки
    @DeleteMapping("/{compId}")
    public void deleteCompilation(
            @PathVariable long compId
    ) {
        log.info("[ADMIN] Delete compilation id={}", compId);
        adminCompilationsService.deleteCompilation(compId);
    }

    //Удаление события из подборки
    @DeleteMapping("/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(
            @PathVariable long compId,
            @PathVariable long eventId
    ) {
        log.info("[ADMIN] Delete event id={} from compilation id={}", eventId, compId);
        adminCompilationsService.deleteEventFromCompilation(compId, eventId);
    }

    //Добавить событие в подборку
    @PatchMapping("/{compId}/events/{eventId}")
    public void addEventToCompilation(
            @PathVariable long compId,
            @PathVariable long eventId
    ) {
        log.info("[ADMIN] Add event id={} to compilation id={}", eventId, compId);
        adminCompilationsService.addEventToCompilation(compId, eventId);
    }

    //Открепить подборку на гланой странице
    @DeleteMapping("/{compId}/pin")
    public void removeCompilationPin(
            @PathVariable long compId
    ) {
        log.info("[ADMIN] Remove compilation pin id={}", compId);
        adminCompilationsService.removeCompilationPin(compId);
    }

    //Закрепить подборку на главной странице
    @PatchMapping("/{compId}/pin")
    public void addCompilationPin(
            @PathVariable long compId
    ) {
        log.info("[ADMIN] Add compilation pin id={}", compId);
        adminCompilationsService.addCompilationPin(compId);
    }
}
