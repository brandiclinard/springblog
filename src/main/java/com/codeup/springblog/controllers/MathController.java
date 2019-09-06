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
        int firstDie = random.nextInt(6) + 1;
        int secondDie = random.nextInt(6) +1;
        int thirdDie = random.nextInt(6) +1;
        int fourthDie = random.nextInt(6) +1;
        int fifthDie = random.nextInt(6) +1;

        String message = "";
        int count = 0;

        if(num == firstDie){
            count = count + 1;
        }

        if(num == secondDie){
            count = count + 1;
        }

        if(num == thirdDie){
            count = count + 1;
        }

        if(num == fourthDie){
            count = count + 1;
        }

        if(num == fifthDie){
            count = count + 1;
        }

        if(num == firstDie || num == secondDie || num == thirdDie || num == fourthDie || num == fifthDie){
            message = "You guess the correct number!";
        }else{
            message = "You guessed the wrong number!";
        }

        viewModel.addAttribute("firstDie", firstDie);
        viewModel.addAttribute("secondDie", secondDie);
        viewModel.addAttribute("thirdDie", thirdDie);
        viewModel.addAttribute("fourthDie", fourthDie);
        viewModel.addAttribute("fifthDie", fifthDie);

        viewModel.addAttribute("message", message);

        return  "<p>You rolled the numbers: " + firstDie + ", " + secondDie + ", " + thirdDie + ", " +fourthDie + ",  and "+ fifthDie + "!     Your guess was number: " + num + "!     Your number matched " + count + " of the dice!     " + message+ "</p>" ;
    }

}
