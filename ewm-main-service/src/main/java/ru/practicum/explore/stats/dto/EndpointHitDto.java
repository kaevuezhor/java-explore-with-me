package ru.practicum.explore.stats.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndpointHitDto {

    private long id;

    private String app;

    private String uri;

    private String ip;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;
}
