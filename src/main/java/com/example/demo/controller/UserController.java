package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/create")
    public String createUserForm(User user) {
        return "create";
    }

    @PostMapping("create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id")Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String updateUserForm(@PathVariable("id")Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "/edit";
    }

    @PostMapping("edit")
    public String update (User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
