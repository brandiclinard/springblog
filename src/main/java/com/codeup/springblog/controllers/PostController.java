package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController{


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
    public String update(@PathVariable long id, @ModelAttribute Post post){
        postDao.save(post);
        return "redirect: /posts";
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
    @ResponseBody
    public String createFormView(){
        return "/post/create";
    }

    @PostMapping("/posts/create")
    public String create(){
        return "posts/show";
    }

}
