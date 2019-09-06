package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody// only use if the purpose will be returning text to the view
    public String landing() {
        return "<h1>THIS IS THE LANDING PAGE!!</h1>";
    }


        @GetMapping("/home")
        public String welcome(Model viewModel) {
            String userSession = "lady";
            String role = "user";

            List<String> colors = new ArrayList<>();
            colors.add("blue");
            colors.add("green");
            colors.add("yellow");


            if(userSession.equals("brandi")){
                role = "admin";
            }
            viewModel.addAttribute("role", role);
            viewModel.addAttribute("colors", colors);
        return "home";
        }

}
