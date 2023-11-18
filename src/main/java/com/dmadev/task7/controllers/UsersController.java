package com.dmadev.task7.controllers;

import com.dmadev.task7.models.User;
import com.dmadev.task7.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"","/","list"})
    public String showAllUsers(Model model, @ModelAttribute("flashMessage") String flashAttribute){
        model.addAttribute("users",userService.getAllUsers());

        return "list";
    }

    @GetMapping(value = "/new")
    public String addUserForm(@ModelAttribute("user")User user){
        return "form";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable(value = "id",required = true) long id, Model model, RedirectAttributes attributes){
        userService.ad
    }







}
