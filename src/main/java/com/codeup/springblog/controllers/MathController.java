package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public long add(@PathVariable long num1, @PathVariable long num2){
        return num1 + num2;
    }

    @GetMapping("/subtract/{num2}/from/{num1}")
    @ResponseBody
    public long subtract(@PathVariable long num1, @PathVariable long num2){
        return num1 - num2;
    }

    @GetMapping("multiply/{num1}/and/{num2}")
    @ResponseBody
    public long multiply(@PathVariable long num1, @PathVariable long num2){
        return num1 * num2;
    }

    @GetMapping("divide/{num1}/by/{num2}")
    @ResponseBody
    public long divide(@PathVariable long num1, @PathVariable long num2){
        return num1 / num2;
    }
}
