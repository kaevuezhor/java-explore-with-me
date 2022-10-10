package ru.practicum.explore.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.like.model.Like;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByEventIdAndUserId(long eventId, long userId);
}
