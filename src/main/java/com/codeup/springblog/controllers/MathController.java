package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

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

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{num}")
    @ResponseBody
    public String numberGuessed(@PathVariable int num, Model viewModel){
        Random random = new Random();
        int numberRolled = random.nextInt(6) + 1;

        String message = "";
        if(num != numberRolled){
            message = "You guessed the wrong answer!";
        }else{
            message = "You guessed the correct answer!";
        }

        viewModel.addAttribute("numberRolled", numberRolled);
        viewModel.addAttribute("message", message);

        return  "<p>You rolled the number: " + numberRolled + "!     Your guess was number: " + num + "!     " + message+ "</p>" ;
    }

}
