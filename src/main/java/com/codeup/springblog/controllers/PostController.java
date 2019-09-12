package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String index(Model viewModel){
        ArrayList<Post> books = new ArrayList<>();
        Post book1 = new Post("The Chestnut Man", "A psychopath is terrorizing Copenhagen.\n" +
                "\n" +
                "His calling card is a “chestnut man”—a handmade doll made of matchsticks and two chestnuts—which he leaves at each bloody crime scene.\n" +
                "\n" +
                "Examining the dolls, forensics makes a shocking discovery—a fingerprint belonging to a young girl, a government minister’s daughter who had been kidnapped and murdered a year ago.\n" +
                "\n" +
                "A tragic coincidence—or something more twisted?\n" +
                "\n" +
                "To save innocent lives, a pair of detectives must put aside their differences to piece together the Chestnut Man’s gruesome clues.\n" +
                "\n" +
                "Because it’s clear that the madman is on a mission that is far from over.\n" +
                "\n" +
                "And no one is safe.");
        Post book2 = new Post("This Tender Land\n", "1932, Minnesota—the Lincoln School is a pitiless place where hundreds of Native American children, forcibly separated from their parents, are sent to be educated. It is also home to an orphan named Odie O’Banion, a lively boy whose exploits earn him the superintendent’s wrath. Forced to flee, he and his brother Albert, their best friend Mose, and a brokenhearted little girl named Emmy steal away in a canoe, heading for the mighty Mississippi and a place to call their own.\n" +
                "\n" +
                "Over the course of one unforgettable summer, these four orphans will journey into the unknown and cross paths with others who are adrift, from struggling farmers and traveling faith healers to displaced families and lost souls of all kinds. With the feel of a modern classic, This Tender Land is an en\u00ADthralling, big-hearted epic that shows how the magnificent American landscape connects us all, haunts our dreams, and makes us whole.");
        Post book3 = new Post("Bringing Down the Duke\n", "England, 1879. Annabelle Archer, the brilliant but destitute daughter of a country vicar, has earned herself a place among the first cohort of female students at the renowned University of Oxford. In return for her scholarship, she must support the rising women's suffrage movement. Her charge: recruit men of influence to champion their cause. Her target: Sebastian Devereux, the cold and calculating Duke of Montgomery who steers Britain's politics at the Queen's command. Her challenge: not to give in to the powerful attraction she can't deny for the man who opposes everything she stands for.\n" +
                "\n" +
                "Sebastian is appalled to find a suffragist squad has infiltrated his ducal home, but the real threat is his impossible feelings for green-eyed beauty Annabelle. He is looking for a wife of equal standing to secure the legacy he has worked so hard to rebuild, not an outspoken commoner who could never be his duchess. But he wouldn't be the greatest strategist of the Kingdom if he couldn't claim this alluring bluestocking without the promise of a ring ... or could he?\n" +
                "\n" +
                "Locked in a battle with rising passion and a will matching her own, Annabelle will learn just what it takes to topple a duke....");

        books.add(book1);
        books.add(book2);
        books.add(book3);

        viewModel.addAttribute("posts", books );
        return "posts/index";



    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewModel){
        Post post = new Post("testing", "testing testing");
        viewModel.addAttribute("post", post);
        return "posts/show";
    }

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
