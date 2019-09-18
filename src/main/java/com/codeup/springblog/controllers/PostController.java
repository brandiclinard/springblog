package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Book;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
//import com.codeup.springblog.repos.CategoryRepository;
import com.codeup.springblog.repos.BookRepository;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController{


    private final PostRepository postDao;
    private final UserRepository userDao;
    private final BookRepository bookDao;

    public PostController(PostRepository postRepository, UserRepository userRepository, BookRepository bookRepository){
        this.postDao = postRepository;
        this.userDao = userRepository;
        this.bookDao = bookRepository;
    }

    @Autowired
    private EmailService emailService;


    //the above two lines are the dependency injection to have access to the db

//    @GetMapping("/posts")
//    public String index(Model viewModel){
//        Iterable<Post> posts = postDao.findAll();
//        viewModel.addAttribute("posts", posts );
//        return "posts/index";
//
//
//    @GetMapping("/posts/{id}")
//    public String show(@PathVariable long id, Model viewModel) {
//        Post post = postDao.findOne(id);
//        viewModel.addAttribute("post", post );
//        return "posts/show";
//    }

//    @GetMapping("/posts/search")
//    public String show(@RequestParam(name = "term") String term, Model viewModel) {
//        List<Post> posts = postDao.searchByTitleOrAuthorLike(term);
//        viewModel.addAttribute("posts", posts);
//        return "posts/index";
//    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("post", postDao.findOne(id));
        return "posts/edit";
    }

    @PostMapping("posts/edit/{id}")
    public String update(@PathVariable long id, @RequestParam(name= "title") String title, @RequestParam(name="body") String body, Model viewModel){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        User userDB = userDao.findOne(userId);
        viewModel.addAttribute("user", userDB);


        Post newPost = postDao.findOne(id);
        newPost.setTitle(title);
        newPost.setBody(body);
        postDao.save(newPost);
        emailService.prepareAndSend(newPost, "Post Edited", String.format("Post with the ID of %d has been edited!", newPost.getId()));

        return "redirect:/posts/" + newPost.getId();
    }


    @GetMapping("/posts/delete/{id}")
    public String toDeletePost(@PathVariable long id){
        Post toDelete = postDao.findOne(id);
        postDao.delete(id);
        emailService.prepareAndSend(toDelete, "Post Deleted", String.format("Post with the ID of %d has been deleted!", toDelete.getId()));

        return "redirect:/books";
    }

    @PostMapping("/posts/delete/{id}")
    public String afterDelete(){

        return "redirect:/books";
    }

    @GetMapping("posts/delete/all")
    public String deleteAllPosts(){
        postDao.deleteAll();
        return "redirect:/posts";
    }

    @PostMapping("/posts/delete/all")
    public String afterDeleteAll(){
        return "redirect:/posts";
    }

    @GetMapping("/posts/create/{id}/bookPost")
    public String createFormView(Model viewModel, @PathVariable long id){
        viewModel.addAttribute("post", new Post());
        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);
        return "/posts/create";
    }

    @PostMapping("/posts/create/{id}/bookPost")
    public String createPost(@ModelAttribute Post post, @ModelAttribute Book book, @PathVariable long id){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(userSession.getId());
        Book bookDB = bookDao.findOne(id);
        post.setUser(userDB);
        post.setBook(bookDB);
//        System.out.println(userDB.getId());
//        System.out.println(bookDB.getId());

        Post savedPost = postDao.save(post);
//        System.out.println(savedPost.getId());
//        System.out.println(savedPost.getUser().getId());
//        System.out.println(savedPost.getBook().getId());


        emailService.prepareAndSend(savedPost, "Post Created", String.format("Post with the ID of %d has been created!", savedPost.getId()));

        return "redirect:/posts/" + savedPost.getId();
    }

}

