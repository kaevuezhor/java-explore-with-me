package ru.practicum.explore.event.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum EventSort {
    EVENT_DATE("eventDate"),
    VIEWS("views"),
    LIKES("likes"),
    DISLIKES("dislikes");

    private final String title;

    public static Optional<EventSort> from(String sortParam) {
        for (EventSort sort : values()) {
            if (sort.name().equalsIgnoreCase(sortParam)) {
                return Optional.of(sort);
            }
        }
        return Optional.empty();
    }
}
