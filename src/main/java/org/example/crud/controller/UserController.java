package org.example.crud.controller;

import org.example.crud.model.User;
import org.example.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "add_user";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id));
        return "update_user";
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/add")
    public String saveUser(@RequestBody User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
