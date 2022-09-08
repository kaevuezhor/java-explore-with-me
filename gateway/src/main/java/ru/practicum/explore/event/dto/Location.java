package ru.practicum.explore.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private long lat;
    private long lon;
}
