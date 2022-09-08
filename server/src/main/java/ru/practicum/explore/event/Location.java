package ru.practicum.explore.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private long lat;
    private long lon;
}
