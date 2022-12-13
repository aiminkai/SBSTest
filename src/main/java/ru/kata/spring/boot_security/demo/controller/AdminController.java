package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "main-page";
    }


   // @GetMapping
//    public String printUsers(@ModelAttribute(value = "newUser") User user, ModelMap model, Principal principal) {
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
//        model.addAttribute("rolesList", roleService.getRolesList());
//        return "main-page";
//        //return "index";
//    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "user-edit-page";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.createUser(user);
        return "redirect:/admin/";
    }

    //Удаление пользователя
    @RequestMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }

    @RequestMapping("/update-info/{id}")
    public String userInfo(Model model, @PathVariable("id") int id) {
        User currentUser = userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "user-edit-page";
    }

//888888888888888888888888888888888888888888888888
//    @PostMapping
//    public String addUser(User newUser) {
//        userService.createUser(newUser);
//        return "redirect:/admin";
//    }

//    @PostMapping(value = "/edit")
//    public String updatedUser(@ModelAttribute(value = "userToEdit") User userToEdit) {
//        userService.updateUser(userToEdit);
//        return "redirect:/admin";
//    }
//
//    @RequestMapping (value = "/delete")
//    public String deletedUser(@ModelAttribute(value = "userToDelete") User userToDelete) {
//        userService.deleteUser(userToDelete);
//        return "redirect:/admin";
//    }
}
