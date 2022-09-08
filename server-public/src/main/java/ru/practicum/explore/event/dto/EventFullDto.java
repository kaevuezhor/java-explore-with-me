package ru.practicum.explore.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.category.CategoryDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventFullDto {
    private String annotation;
    private CategoryDto category;
    private long confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private long id;
    private UserShortDto initiator;
    private boolean paid;
    private int participantLimit;
    private LocalDateTime publishedOn;
    private boolean requestModeration;
    private EventState state;
    private long views;

}
