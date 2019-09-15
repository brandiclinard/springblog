package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.Season;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.BookRepository;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.SeasonRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookDao;
    private final SeasonRepository seasonDao;
    private final PostRepository postDao;
    private final UserRepository userDao;

    public BookController(BookRepository bookRepository, SeasonRepository seasonRepository, PostRepository postRepository, UserRepository userRepository){
        this.bookDao = bookRepository;
        this.seasonDao = seasonRepository;
        this.postDao = postRepository;
        this.userDao = userRepository;
    }

    @Autowired
    private EmailService emailService;

    @GetMapping("/books")
    public String index(Model viewModel){
        Iterable<Book> books = bookDao.findAll();
        viewModel.addAttribute("books", books );
        Iterable<Season> seasons = seasonDao.findAll();
        viewModel.addAttribute("seasons", seasons);
        return "books/index";
    }

       @GetMapping("/books/search")
    public String showByAuthorOrTitle(@RequestParam(name = "term") String term, Model viewModel) {
        List<Book> books = bookDao.searchByTitleOrAuthor(term);
        viewModel.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/books/seasonSearch")
    public String showSeasonBooks(@RequestParam(name = "seasonSelection") long seasonSelection, Model viewModel) {
        Season season = seasonDao.findOne(seasonSelection);
        viewModel.addAttribute("season", season);
        List<Book> books = bookDao.findBySeason(season);
        viewModel.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("books/{id}")
    public String showBook(@PathVariable long id, Model viewModel){
        Book book= bookDao.findOne(id);
        viewModel.addAttribute("book", book );
        List<Post> posts = postDao.findByBook(book);
        viewModel.addAttribute("posts", posts );
        return "books/show";
    }

    @GetMapping("/books/userPosts/{id}")
    public String showUserBookPosts(@PathVariable long id, Model viewModel){
        Book book = bookDao.findOne(id);
        viewModel.addAttribute("book", book);
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSession.getId();
        User user = userDao.findOne(userId);
        viewModel.addAttribute("user", user);
        List<Post> posts = postDao.findByUserAndBook(user, book);
        viewModel.addAttribute("posts", posts);
        return("books/show");
    }

    @GetMapping("/books/create")
    public String createBookFormView(Model viewModel){
        viewModel.addAttribute("book", new Book());
        return "/books/create";
    }

    @PostMapping("/books/create")
    public String createBook(@ModelAttribute Book book){

        Book savedBook = bookDao.save(book);

//        emailService.prepareAndSend(savedBook, "Book Created", String.format("Book with the ID of %d has been created!", savedBook.getId()));

        return "redirect:/books/" + savedBook.getId();
    }


}
