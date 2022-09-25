package ru.practicum.explore.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.dto.EndpointHit;
import ru.practicum.explore.dto.ViewsStats;
import ru.practicum.explore.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {

    private final StatsRepository statsRepository;

    @PostMapping("/hit")
    public void hit(
            @RequestBody EndpointHit endpointHit
            ) {
        log.info("Hit {}", endpointHit);
        statsRepository.save(endpointHit);
    }

    @GetMapping("/stats")
    public List<ViewsStats> stats(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam (required = false) List<String> uris,
            @RequestParam (required = false, defaultValue = "false") boolean unique
            ) {
        log.info("Stats start={}, end={}, uris={}, unique={}", start, end, uris, unique);


        if (uris == null && unique) {
            return statsRepository.findAllUnique(start, end);
        } else if (uris == null) {
            return statsRepository.findAll(start, end);
        } else if (unique) {
            List<ViewsStats> viewsStats = new ArrayList<>();
            for (String uri : uris) {
                ViewsStats stats = statsRepository.findUnique(start, end, uri);
                viewsStats.add(stats);
            }
            return viewsStats;
        } else {
            List<ViewsStats> viewsStats = new ArrayList<>();
            for (String uri : uris) {
                ViewsStats stats = statsRepository.find(start, end, uri);
                viewsStats.add(stats);
            }
            return viewsStats;
        }
    }
}
