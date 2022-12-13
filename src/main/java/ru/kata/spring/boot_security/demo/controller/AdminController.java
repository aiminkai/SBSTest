package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String printUsers(@ModelAttribute(value = "newUser") User user, ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("rolesList", roleService.getRolesList());
        return "index";
    }

    @PostMapping
    public String addUser(User newUser) {
        userService.addUser(newUser);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit")
    public String updatedUser(@ModelAttribute(value = "userToEdit") User userToEdit) {
        userService.updateUser(userToEdit);
        return "redirect:/admin";
    }

    @RequestMapping (value = "/delete")
    public String deletedUser(@ModelAttribute(value = "userToDelete") User userToDelete) {
        userService.removeUser(userToDelete);
        return "redirect:/admin";
    }
}
