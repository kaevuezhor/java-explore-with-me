package ru.practicum.explore.compilation.service;

import ru.practicum.explore.compilation.dto.CompilationDto;

import java.util.List;

public interface PublicCompilationsService {
    public List<CompilationDto> getCompilations(Boolean pinned, int from, int size);

    public CompilationDto getCompilation(long compId);
}
