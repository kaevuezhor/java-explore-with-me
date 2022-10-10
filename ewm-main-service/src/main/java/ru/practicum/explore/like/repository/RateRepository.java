package ru.practicum.explore.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.like.model.Rate;

import java.util.Optional;

public interface RateRepository<T extends Rate> extends JpaRepository<T, Long> {

    Optional<T> findByEventIdAndUserId(long eventId, long userId);
}
