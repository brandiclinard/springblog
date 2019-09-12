package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController{


    private final PostRepository postDao;

    public PostController(PostRepository postRepository){
        postDao = postRepository;
    }

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
    public String createFormView(){
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body){
        Post createPost = new Post();
        createPost.setTitle(title);
        createPost.setBody(body);
        Post savedPost = postDao.save(createPost);
        return "redirect:/posts/" + savedPost.getId();
    }

}
