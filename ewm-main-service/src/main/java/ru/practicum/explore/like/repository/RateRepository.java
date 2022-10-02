package ru.practicum.explore.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.like.model.Rate;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {

    Optional<Rate> findByEventIdAndUserId(long eventId, long userId);
}
