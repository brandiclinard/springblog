package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.StatusRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private final StatusRepository statusDao;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, StatusRepository statusRepository) {
        this.userDao = userRepository;
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
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("users/edit/{id}")
    public String editUserProfile(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("user", userDao.findOne(id));
        return("users/edit");
    }

    @PostMapping("users/edit/{id}")
    public String update(@PathVariable long id, @RequestParam(name= "username") String username, @RequestParam(name="email") String email, @RequestParam(name= "password") String password, Model viewModel){
        User newUser = userDao.findOne(id);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        userDao.save(newUser);
        return "redirect:/profileView" + newUser.getId();
    }

//    @GetMapping("/profile")
//    public String userPage(Model viewModel){
//        viewModel.addAttribute("user", new User());
//        return "users/profile";
//    }

    @GetMapping("/profileView")
    public String profileView(Model viewModel, HttpServletRequest request){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        User userDB = userDao.findOne(userId);

//        HttpSession session = request.getSession();
//        session.setAttribute("isAdmin", userDB.isAdmin());

        viewModel.addAttribute("user", userDB);

        return("users/profile");
    }

//    @PostMapping("users/profile/{id}")
//    public String viewProfile(@ModelAttribute User user){
//        return ""
//    }
}
