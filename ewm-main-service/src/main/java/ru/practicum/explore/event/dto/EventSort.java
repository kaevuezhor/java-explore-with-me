package ru.practicum.explore.event.dto;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum EventSort {
    EVENT_DATE("eventDate"),
    VIEWS("views"),
    RATE("rate");

    private String title;

    EventSort(String title) {
        this.title = title;
    }
    public static Optional<EventSort> from(String sortParam) {
        for (EventSort sort : values()) {
            if (sort.name().equalsIgnoreCase(sortParam)) {
                return Optional.of(sort);
            }
        }
        return Optional.empty();
    }
}
