package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
//import com.codeup.springblog.repos.CategoryRepository;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController{


    private final PostRepository postDao;
    private final UserRepository userDao;


    public PostController(PostRepository postRepository, UserRepository userRepository){
        this.postDao = postRepository;
        this.userDao = userRepository;
    }

    @Autowired
    private EmailService emailService;


    //the above two lines are the dependency injection to have access to the db

    @GetMapping("/posts")
    public String index(Model viewModel){
        Iterable<Post> posts = postDao.findAll();
        viewModel.addAttribute("posts", posts );
        return "posts/index";


    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewModel){
        Post post = postDao.findOne(id);
        viewModel.addAttribute("post", post );
        return "posts/show";
    }

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
        Post newPost = postDao.findOne(id);
        newPost.setTitle(title);
        newPost.setBody(body);
        postDao.save(newPost);
        return "redirect:/posts/" + newPost.getId();
    }


    @GetMapping("/posts/delete/{id}")
    public String toDeletePost(@PathVariable long id){
        postDao.delete(id);
        return "redirect:/posts";
    }

    @PostMapping("/posts/delete/{id}")
    public String afterDelete(){
        return "redirect:/posts";
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

    @GetMapping("/posts/create")
    public String createFormView(Model viewModel){
    viewModel.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(userSession.getId());

        post.setUser(userDB);

        Post savedPost = postDao.save(post);

        emailService.prepareAndSend(savedPost, "Post Created", String.format("Post with the ID of %d has been created!", savedPost.getId()));

        return "redirect:/posts/" + savedPost.getId();
    }

}

