package ru.practicum.explore.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.user.dto.UserDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Поиск пользователей
    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("Get users users={}, from={}, size={}", users, from, size);
        return userService.getUsers(users, from, size);
    }

    //Добавление нового пользователя
    @PostMapping
    public UserDto postUser(
            @RequestBody UserDto userDto
    ) {
        log.info("Post user user={}", userDto);
        return userService.postUser(userDto);
    }

    //Удаление пользователя
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable long userId
    ) {
        log.info("Delete user id={}", userId);
        userService.deleteUser(userId);
    }
}
