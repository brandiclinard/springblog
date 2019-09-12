package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.StatusRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private final StatusRepository statusDao;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, StatusRepository statusRepository) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.statusDao = statusRepository;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model viewModel){ // use this model for controller to view
        viewModel.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){// use this model for view to controller
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("users/edit/{id}")
    public String editUser(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("user", users.findOne(id));
        return("users/edit");
    }

    @PostMapping("users/edit/{id}")
    public String update(@PathVariable long id, @RequestParam(name= "username") String username, @RequestParam(name="email") String email, @RequestParam(name= "password") String password, Model viewModel){
        User newUser = users.findOne(id);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        users.save(newUser);
        return "redirect:/users/profile" + newUser.getId();
    }
}
