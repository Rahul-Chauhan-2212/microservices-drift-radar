package com.driftlab.user_service.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping("/{id}")
  public Map<String, Object> getUser(@PathVariable String id) {

    Map<String, Object> user = new HashMap<>();
    user.put("id", id);
    user.put("name", "User-" + id);
    user.put("tier", "GOLD");

    return user;
  }
}