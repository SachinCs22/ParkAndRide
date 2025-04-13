package com.example.ParkAndRide.ParkAndRide.controller;


import com.example.ParkAndRide.ParkAndRide.entity.User;
import com.example.ParkAndRide.ParkAndRide.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @GetMapping
    public List<User> getAll() { return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<User> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public User create(@RequestBody User user) { return service.save(user); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.deleteById(id); }
}
