package ru.practicum.explore.like.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.explore.event.dto.EventState;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.like.model.Rate;
import ru.practicum.explore.like.repository.RateRepository;
import ru.practicum.explore.like.service.PrivateRateService;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrivateRateServiceImpl implements PrivateRateService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    private final RateRepository rateRepository;

    @Override
    public void like(long userId, long eventId) {
        User user = userRepository.findById(userId).orElseThrow();
        Event event = eventRepository.getReferenceById(eventId);
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new RuntimeException();
        }
        if (event.getInitiator().equals(user)) {
            throw new RuntimeException();
        }
        Optional<Rate> savedRate = rateRepository.findByEventIdAndUserId(eventId, userId);
        if (savedRate.isEmpty()) {
            Rate newRate = rateRepository.save(new Rate(0, event, user, true));
            event.getRates().add(newRate);
            eventRepository.save(event);
            return;
        }
        Rate rate = rateRepository.getReferenceById(savedRate.get().getId());
        if (rate.isLiked()) {
            if (event.getRates().contains(rate)) {
                event.getRates().remove(rate);
            } else {
                event.getRates().add(rate);
            }
        } else {
            event.getRates().remove(rate);
            rate.setLiked(true);
            event.getRates().add(rateRepository.save(rate));
        }
        eventRepository.save(event);
    }

    @Override
    public void dislike(long userId, long eventId) {
        User user = userRepository.findById(userId).orElseThrow();
        Event event = eventRepository.getReferenceById(eventId);
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new RuntimeException();
        }
        if (event.getInitiator().equals(user)) {
            throw new RuntimeException();
        }
        Optional<Rate> savedRate = rateRepository.findByEventIdAndUserId(eventId, userId);
        if (savedRate.isEmpty()) {
            Rate newRate = rateRepository.save(new Rate(0, event, user, false));
            event.getRates().add(newRate);
            eventRepository.save(event);
            return;
        }
        Rate rate = rateRepository.getReferenceById(savedRate.get().getId());
        if (!rate.isLiked()) {
            if (event.getRates().contains(rate)) {
                event.getRates().remove(rate);
            } else {
                event.getRates().add(rate);
            }
        } else {
            event.getRates().remove(rate);
            rate.setLiked(false);
            event.getRates().add(rateRepository.save(rate));
        }
        eventRepository.save(event);
    }

}
