package com.mfreimueller.controller;

import com.mfreimueller.dto.CreateUserDto;
import com.mfreimueller.dto.UserDto;
import com.mfreimueller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
