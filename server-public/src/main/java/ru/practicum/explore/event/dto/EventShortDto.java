package ru.practicum.explore.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.category.CategoryDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventShortDto {
    private String annotation;
    private CategoryDto category;
    private long confirmedRequests;
    private LocalDateTime eventDate;
    private long id;
    private UserShortDto initiator;
    private boolean paid;
    private String title;
    private long views;
}
