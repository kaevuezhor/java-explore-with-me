package ru.practicum.explore.compilation.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.service.PublicCompilationsService;

import java.util.List;

@Service
public class PublicCompilationsServiceImpl implements PublicCompilationsService {
    @Override
    public List<CompilationDto> getCompilations(Boolean pinned, int from, int size) {
        return null;
    }

    @Override
    public CompilationDto getCompilation(long compId) {
        return null;
    }
}
