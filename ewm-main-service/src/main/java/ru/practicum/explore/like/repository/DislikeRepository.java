package ru.practicum.explore.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.like.model.Dislike;

import java.util.Optional;

public interface DislikeRepository extends JpaRepository<Dislike, Long> {

    Optional<Dislike> findByEventIdAndUserId(long eventId, long userId);
}
