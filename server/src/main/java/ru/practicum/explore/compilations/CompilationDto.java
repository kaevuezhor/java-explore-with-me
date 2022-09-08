package ru.practicum.explore.compilations;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.explore.event.EventShortDto;

import java.util.List;

@Data
@AllArgsConstructor
public class CompilationDto {
    private List<EventShortDto> events;
    private long id;
    private boolean pinned;
    private String title;
}
