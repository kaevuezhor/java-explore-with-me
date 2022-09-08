package ru.practicum.explore.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpdateEventRequest {
    private String annotation;
    private boolean annotationNeedUpdate;

    private Long category;
    private boolean categoryNeedUpdate;

    private String description;
    private boolean descriptionNeedUpdate;

    private LocalDateTime eventDate;
    private boolean eventDateNeedUpdate;

    private Long eventId;

    private Boolean paid;
    private boolean paidFlagNeedUpdate;

    private Integer participantLimit;
    private boolean participantLimitNeedUpdate;

    private String title;
    private boolean titleNeedUpdate;
}
