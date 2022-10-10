package ru.practicum.explore.like.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.event.dto.EventState;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.repository.EventRepository;
import ru.practicum.explore.like.model.Dislike;
import ru.practicum.explore.like.model.Like;
import ru.practicum.explore.like.repository.RateRepository;
import ru.practicum.explore.like.service.PrivateRateService;
import ru.practicum.explore.request.model.ParticipationRequest;
import ru.practicum.explore.user.model.User;
import ru.practicum.explore.user.repository.UserRepository;

import java.rmi.AccessException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrivateRateServiceImpl implements PrivateRateService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    private final RateRepository<Like> likeRepository;

    private final RateRepository<Dislike> dislikeRepository;

    @Override
    @Transactional
    public void like(long userId, long eventId) throws IllegalAccessException, AccessException {
        Event event = checkEvent(eventId);
        User user = checkUser(userId, event);
        Optional<Like> savedLike = likeRepository.findByEventIdAndUserId(eventId, userId);
        Optional<Dislike> savedDislike = dislikeRepository.findByEventIdAndUserId(eventId, userId);

        if (savedLike.isEmpty() && savedDislike.isEmpty()) {
            //Нет оценок - поставить лайк
            Like like = likeRepository.save(new Like(0, event, user));
            event.getLikes().add(like);
        } else if (savedLike.isEmpty()) {
            //Стоит дизлайк - убрать дизлайк
            Dislike dislike = savedDislike.get();
            event.getDislikes().remove(dislike);
            dislikeRepository.delete(dislike);
            //Поставить лайк
            Like like = likeRepository.save(new Like(0, event, user));
            event.getLikes().add(like);
        } else if (savedDislike.isEmpty()) {
            //Стоит лайк - убрать лайк
            Like like = savedLike.get();
            event.getLikes().remove(like);
            likeRepository.delete(like);
        }
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void dislike(long userId, long eventId) throws IllegalAccessException, AccessException {
        Event event = checkEvent(eventId);
        User user = checkUser(userId, event);
        Optional<Like> savedLike = likeRepository.findByEventIdAndUserId(eventId, userId);
        Optional<Dislike> savedDislike = dislikeRepository.findByEventIdAndUserId(eventId, userId);

        if (savedLike.isEmpty() && savedDislike.isEmpty()) {
            //Нет оценок - поставить дизлайк
            Dislike dislike = dislikeRepository.save(new Dislike(0, event, user));
            event.getDislikes().add(dislike);
        } else if (savedDislike.isEmpty()) {
            //Стоит лайк - убрать лайк
            Like like = savedLike.get();
            event.getLikes().remove(like);
            likeRepository.delete(like);
            //Поставить дизлайк
            Dislike dislike = dislikeRepository.save(new Dislike(0, event, user));
            event.getDislikes().add(dislike);
        } else if (savedLike.isEmpty()) {
            //Стоит дизлайк - убрать дизлайк
            Dislike dislike = savedDislike.get();
            event.getDislikes().remove(dislike);
            dislikeRepository.delete(dislike);
        }
        eventRepository.save(event);
    }

    private Event checkEvent(long eventId) throws IllegalAccessException {
        Event event = eventRepository.getReferenceById(eventId);
        if (!EventState.PUBLISHED.equals(event.getState())) {
            throw new IllegalStateException("Event should be PUBLISHED");
        }
        if (event.getEventDate().isBefore(LocalDateTime.now())) {
            throw new IllegalAccessException("You can't rate future Event");
        }
        return event;
    }

    private User checkUser(long userId, Event event) throws AccessException, IllegalAccessException {
        User user = userRepository.getReferenceById(userId);
        if (event.getInitiator().equals(user)) {
            throw new IllegalAccessException("You can't rate your own Event");
        }
        if (!isUserParticipated(event, user)) {
            throw new AccessException("You can't rate Event without participation");
        }
        return user;
    }

    private boolean isUserParticipated(Event event, User user) {
        for (ParticipationRequest request : event.getConfirmedRequests()) {
            if (request.getRequester().equals(user)) {
                return true;
            }
        }
        return false;
    }
}
