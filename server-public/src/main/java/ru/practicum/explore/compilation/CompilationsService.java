package ru.practicum.explore.compilation;

import ru.practicum.explore.compilation.dto.CompilationDto;

import java.util.List;

public interface CompilationsService {
    List<CompilationDto> getCompilations(Boolean pinned, int from, int size);

    CompilationDto getCompilation(long compId);
}
