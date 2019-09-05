package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class HelloController {
   private long count = 0;


    //GET METHODS
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h2>Hello from Spring!</h2>";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name) {
        return "<h1>Hello " + name + "!</h1>";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable long number) { // you do not have to parse numbers in Spring with get requests
        count += number;
        return " Count is at : " + count + "!";
    }

//POST METHODS
    @PostMapping("/hello")
    @ResponseBody
    public String goodbye(){
        return "Goodbye from Spring";
    }

    @PostMapping("/goodbye")
    @ResponseBody
    public String goodbyeGoodbye() {
        return "You waved goodbye";
    }


}