package ru.practicum.explore.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.service.AdminUserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class AdminUserController {

    private final AdminUserService adminUserService;

    //Поиск пользователей
    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> ids,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") int from,
            @Positive @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("[ADMIN] Get users users={}, from={}, size={}", ids, from, size);
        return adminUserService.getUsers(ids, from, size);
    }

    //Добавление нового пользователя
    @PostMapping
    public UserDto postUser(
            @RequestBody @Valid UserDto userDto
    ) {
        log.info("[ADMIN] Post user user={}", userDto);
        return adminUserService.postUser(userDto);
    }

    //Удаление пользователя
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable long userId
    ) {
        log.info("[ADMIN] Delete user id={}", userId);
        adminUserService.deleteUser(userId);
    }
}
