package ru.practicum.explore.like.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.user.model.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "likes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private boolean liked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return id == rate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
