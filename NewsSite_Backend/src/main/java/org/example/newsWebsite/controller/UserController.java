package org.example.newsWebsite.controller;

import org.example.newsWebsite.model.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("add")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return null;
    }

    @GetMapping("all")
    public List<UserDto> getAllUsers() {
        return null;
    }

    @GetMapping("ById")
    public UserDto getUserById(@RequestParam(name = "id") Long id) {
        return null;
    }

    @GetMapping("ByUsername")
    public UserDto getUserById(@RequestParam String username) {
        return null;
    }

    @PutMapping("editU")
    public UserDto editUser(@RequestBody UserDto userDto) {
        return null;
    }

    @DeleteMapping("delete")
    public boolean deleteUser(@RequestParam Long id) {
        return false;
    }
}
