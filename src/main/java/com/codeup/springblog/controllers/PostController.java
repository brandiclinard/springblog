package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postRepository){
        postDao = postRepository;
    }

    @GetMapping("/posts")
    public String index(Model viewModel){
        Iterable<Post> posts = postDao.findAll();
        viewModel.addAttribute("posts", posts );
        return "posts/index";
    }

    @GetMapping("/posts")
    public String index(@PathVariable long id, Model viewModel){
        Post post = postDao.findOne(id);
        viewModel.addAttribute("post", post );
        return "posts/index";
    }

//    @GetMapping("/posts/{id}")
//    public String show(@PathVariable long id, Model viewModel){
//        Post post = new Post(1,"testing", "testing testing");
//        viewModel.addAttribute("post", post);
//        return "posts/show";
//    }

//    @GetMapping("posts/create")
//    @ResponseBody
//    public String createFormView(){
//        return "<h1>VIEW THE FORM FOR CREATING A POST</h1>";
//    }
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String create(){
//        return "CREATE A NEW POST";
//    }

}
