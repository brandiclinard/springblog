package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.models.Status;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.BookRepository;
import com.codeup.springblog.repos.StatusRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private final StatusRepository statusDao;
    private final BookRepository bookDao;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, StatusRepository statusRepository, BookRepository bookRepository) {
        this.userDao = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.statusDao = statusRepository;
        this.bookDao = bookRepository;
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
    public String update(@PathVariable long id, @ModelAttribute User user){

        User userSesssion = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long userId = userSesssion.getId();
//        boolean isAdmin = userSesssion.isAdmin();
//        User userDB = userDao.findOne(userSesssion.getId());
        user.setId(userSesssion.getId());

//        User original = userDao.findOne(id);
//        user.setId(original.getId());
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);

        User updatedUser = userDao.save(user);
//        user.setPassword(original.getPassword());
//        user.setUsername(original.getUsername());
//        userDao.save(user);


//        if(isAdmin) {
//            return "redirect: /admindashboard";
//        }else{
//            return "redirect:/profileView";
//        }
        return "redirect:/login?edit";
    }

// THIS WORKS
    @GetMapping("/profileView")
    public String profileView(Model viewModel){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        User userDB = userDao.findOne(userId);

//        HttpSession session = request.getSession();
//        session.setAttribute("isAdmin", userDB.isAdmin());

        viewModel.addAttribute("user", userDB);
        List<Book> currentBooks = bookDao.currentBooksByUserId(userId);
        List<Book> wishBooks = bookDao.wishBooksByUserId(userId);
        List<Book> completeBooks = bookDao.completeBooksByUserId(userId);
        viewModel.addAttribute("currentBooks", currentBooks);
        viewModel.addAttribute("wishBooks", wishBooks);
        viewModel.addAttribute("completeBooks", completeBooks);



        return("users/profile");
    }


//    @GetMapping("user/profileView")
//    public String userProfileView(Model viewModel){
//        viewModel.addAttribute("user", new User());
//        return "redirect:/profileView";
//    }


}
