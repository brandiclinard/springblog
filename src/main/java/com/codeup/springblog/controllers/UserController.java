package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.models.Status;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.BookRepository;
import com.codeup.springblog.repos.StatusRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EmailService emailService;

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
        user.setId(userSesssion.getId());
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);

        User updatedUser = userDao.save(user);

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

    @GetMapping("profile/update/{id}/current")
    public String updateCurrentList(Model viewModel, @PathVariable long id){
        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);

        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("current");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }

    @PostMapping("profile/update/{id}/current")
    public String  updateCurrentList() {

        return "redirect:/profileView";
    }

    @GetMapping("profile/update/{id}/wish")
    public String updateWishList(Model viewModel, @PathVariable long id){
        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);

        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("wish");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }

    @PostMapping("profile/update/{id}/wish")
    public String  updateWishList() {

        return "redirect:/profileView";
    }

    @GetMapping("profile/update/{id}/complete")
    public String updateCompleteList(Model viewModel, @PathVariable long id){
        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);

        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("complete");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }

    @PostMapping("profile/update/{id}/complete")
    public String  updateCompleteList() {

        return "redirect:/profileView";
    }

    @GetMapping("profile/remove/{id}/current")
    public String removeCurrentList(Model viewModel, @PathVariable long id){

        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.currentStatusObjectId(userId, id);
        statusDao.delete(statusId);

//        emailService.prepareAndSend(toDelete, "Post Deleted", String.format("Post with the ID of %d has been deleted!", toDelete.getId()));

        return "redirect:/profileView";
    }

    @PostMapping("profile/remove/{id}/current")
    public String removeCurrentList(){
        return"redirect:/profileView";
    }

    @GetMapping("profile/remove/{id}/wish")
    public String removeWishList(Model viewModel, @PathVariable long id){

        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.wishStatusObjectId(userId, id);
        statusDao.delete(statusId);


        return "redirect:/profileView";
    }

    @PostMapping("profile/remove/{id}/wish")
    public String removeWishList(){
        return"redirect:/profileView";
    }

    @GetMapping("profile/remove/{id}/complete")
    public String removeCompleteList(Model viewModel, @PathVariable long id){

        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.completeStatusObjectId(userId, id);
        statusDao.delete(statusId);

//        emailService.prepareAndSend(toDelete, "Post Deleted", String.format("Post with the ID of %d has been deleted!", toDelete.getId()));

        return "redirect:/profileView";
    }

    @PostMapping("profile/remove/{id}/complete")
    public String removeCompleteList(){
        return"redirect:/profileView";
    }

    @GetMapping("profile/move/{id}/current-wish")
    public String moveCurrentWish(Model viewModel, @PathVariable long id){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.currentStatusObjectId(userId, id);
        statusDao.delete(statusId);

        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("wish");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }

    @GetMapping("profile/move/{id}/current-complete")
    public String moveCurrentComplete(Model viewModel, @PathVariable long id){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.currentStatusObjectId(userId, id);
        statusDao.delete(statusId);

        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("complete");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }

    @GetMapping("profile/move/{id}/wish-current")
    public String moveWishCurrent(Model viewModel, @PathVariable long id){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.wishStatusObjectId(userId, id);
        statusDao.delete(statusId);

        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("current");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }

    @GetMapping("profile/move/{id}/wish-complete")
    public String moveWishComplete(Model viewModel, @PathVariable long id){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.wishStatusObjectId(userId, id);
        statusDao.delete(statusId);

        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("complete");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }

    @GetMapping("profile/move/{id}/complete-current")
    public String moveCompleteCurrent(Model viewModel, @PathVariable long id){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.completeStatusObjectId(userId, id);
        statusDao.delete(statusId);

        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("current");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }
    @GetMapping("profile/move/{id}/complete-wish")
    public String moveCompleteWish(Model viewModel, @PathVariable long id){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        long statusId = statusDao.completeStatusObjectId(userId, id);
        statusDao.delete(statusId);

        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);
        User userDB = userDao.findOne(userId);

        Status status = new Status();
        status.setUser(userDB);
        status.setBook(book);
        status.setName("wish");

        Status saveStatus = statusDao.save(status);
        return "redirect:/profileView";
    }

    @GetMapping("profile/delete")
    public String deleteProfile(Model viewModel){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        User userDB = userDao.findOne(userId);
        userDao.delete(userId);

        emailService.prepareAndSend(userDB, "Profile Deleted", String.format("The account associated with this email and the username: %s has been deleted!", userDB.getUsername()));

        return "redirect:/login";
    }
}
