package ru.practicum.explore.event;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.explore.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
