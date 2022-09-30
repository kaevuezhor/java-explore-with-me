package ru.practicum.explore.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.event.model.Location;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateEventRequest {
        private String annotation;
        private Long category;
        private String description;
        @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
        private LocalDateTime eventDate;
        private Location location;
        private Boolean paid;
        private Integer participantLimit;
        private Boolean requestModeration;
        private String title;
}
