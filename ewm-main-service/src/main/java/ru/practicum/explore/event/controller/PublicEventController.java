package ru.practicum.explore.event.controller;

import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.EventSort;
import ru.practicum.explore.event.service.impl.PublicEventServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
public class PublicEventController {

    private final PublicEventServiceImpl publicEventService;

    //Получение событий с возможностью фильтрации
    @GetMapping
    public List<EventShortDto> getEvents(
            HttpServletRequest request,
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) @DateTimeFormat LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat LocalDateTime rangeEnd,
            @RequestParam(required = false, defaultValue = "true") boolean onlyAvailable,
            @RequestParam(required = false) String sortParam,
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("client ip: {}", request.getRemoteAddr());
        log.info("endpoint path: {}", request.getRequestURI());
        EventSort sort;
        if (sortParam == null) {
            sort = EventSort.EVENT_DATE;
        } else {
            sort = EventSort.from(sortParam)
                    .orElseThrow(() -> new IllegalArgumentException(sortParam));
        }
        log.info(
                "Get events text={}, categories={}, paid={}, rangeStart={}, rangeEnd={}, onlyAvailable={}, sort={}, from={}, size={}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size
        );
        return publicEventService.getEvents(request, text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    //Получение подробной информации об опубликованном событии по его идентификатору
    @GetMapping("/{id}")
    public EventFullDto getEvent(
            HttpServletRequest request,
            @PathVariable int id
    ) {
        log.info("client ip: {}", request.getRemoteAddr());
        log.info("endpoint path: {}", request.getRequestURI());
        log.info("Get event id={}", id);
        return publicEventService.getEvent(request, id);
    }
}
