package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String index(){
        return "<h1>POSTS INDEX PAGE</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individual(){
        return "<h1>VIEW AN INDIVIDUAL POST</h1>";
    }

    @GetMapping("posts/create")
    @ResponseBody
    public String createFormView(){
        return "<h1>VIEW THE FORM FOR CREATING A POST</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "CREATE A NEW POST";
    }

}
