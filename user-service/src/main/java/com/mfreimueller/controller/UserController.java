package com.mfreimueller.controller;

import com.mfreimueller.dto.CreateUserDto;
import com.mfreimueller.dto.UpdateUserDto;
import com.mfreimueller.dto.UserDto;
import com.mfreimueller.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) { return userService.getUser(userId); }

    /// Returns a list of all active users. This method
    /// excludes any deleted users.
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/deleted")
    public List<UserDto> getAllDeletedUsers() { return userService.getAllDeletedUsers(); }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(userId, updateUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
