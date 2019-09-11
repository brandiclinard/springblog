package com.codeup.springblog;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.models.Category;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

//@Component
//public class Seeder implements CommandLineRunner {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//
//    @Value("${app.env}")
//    private String environment;
//
//    public Seeder(PostRepository postDao, UserRepository userDao) {
//        this.postDao = postDao;
//        this.userDao = userDao;
//    }
//
//    // generate a list of users and return it after saving
//    private List<User> seedUsers() {
//        List<User> users = Arrays.asList(
//                new User(1,"Rory","rory@gg.com", "rory"),
//                new User(2,"Lor", "lor@gg.com", "lor"),
//                new User(3,"Luke", "luke@Qgg.com", "luke"),
//                new User(4,"Logan", "logan@gg.com", "logan"),
//                new User(5,"Jess", "jess@gg.com", "jess")
//        );
//        userDao.save(users);
//        return users;
//    }
//
//// generate a list of categories and return it after saving
////    private List<Category> seedCategories() {
////        List<Category> categories = Arrays.asList(
////                new Category("Season One", List [1]),
////                new Category("Season Two", List [2]),
////                new Category("Season Three", List [3]),
////                new Category("Season Four", List [4]),
////                new Category("Season Five", List [5]),
////                new Category("Season Six", List [6]),
////                new Category("Season Seven", List [7])
////                );
////        userDao.save(categories);
////        return categories;
////    }
//
////    generate a list of books
//    private void seedBooks(List<Book> books){
//        Book newBook = new Book();
//        List<Book> books = Arrays.asList(
//                new Book("On the Road", "Jack Kerouac", ""),
//                new Book("The Adventures of Heckleberry Finn", "Mark Twain", ""),
//                new Book("Rosemary's Baby", "Ira Levin", ""),
//                new Book("Moby Dick", "Herman Melville", ""),
//                new Book("Madame Bovary", "Gustave Flaubert", ""),
//                new Book("The LIttle Match Girl", "Hans Christian Anderson", ""),
//                new Book("The Hunchback of Notre Dame", "Victor Hugo", ""),
//                new Book("War and Peace", "Leo Tolstoy", ""),
//                new Book("David Copperfield", "Charles Dickens", ""),
//                new Book("Great Expectations", "Charles Dickens", ""),
//                new Book("A Tale of Two Cities", "Charles Dickens", ""),
//                new Book("Little Dorrit", "Charles Dickens", ""),
//                new Book("Harry Potter & the Goblet of Fire", "J.K. Rowling", ""),
//                new Book("The Shining", "Stephen King", ""),
//                new Book("Peyton Place", "Grace Metalious", ""),
//                new Book("A Mencken's Chrestomathy", "H.L. Mencken", ""),
//                new Book("My life as Author and Editor", "H.L. Mencken", ""),
//                new Book("The Comedy of Errors", "William Shakespeare", ""),
//                new Book("The Sonnets", "William Shakespeare", ""),
//                new Book("A Room of One's Own", "Virginia Woolf", ""),
//                new Book("Valley of the Dolls","Jacquline Susann",""),
//                new Book("Ethan Frome","Edith Wharton",""),
//                new Book("The Age of Innocence","Edith Wharton",""),
//                new Book("The Crucible","Arthur Miller",""),
//                new Book("Don Quixote","Miguel de Cervantes",""),
//                new Book("Northanger Abbey","Jane Austen",""),
//                new Book("Fear and Loathing in Las Vegas","Hunter S. Thompson",""),
//                new Book("Jane Eyre","Charlotte Bronte",""),
//                new Book("A Streetcar Named Desire","Tennessee Williams",""),
//                new Book("The Group","Mary McCarthy",""),
//                new Book("The Outsiders","S.E. Hinton",""),
//                new Book("The Portable Dorothy Parker","Dorthy Parker",""),
//                new Book("The Miracle Worker","William Gibson",""),
//                new Book("The Metamorphosis","Franz Kafka",""),
//                new Book("Swann's Way","Marcel Proust",""),
//                new Book("Timeline","Michael Crichton",""),
//                new Book("The New Poems of Emily Dickinson","Emily Dickinson",""),
//                new Book("The Unabridged Journals of Sylvia Plath 1950- 1962","Sylvia Plath",""),
//                new Book("Carrie","Stephen King",""),
//                new Book("Macbeth","William Shakespeare",""),
//                new Book("A Streetcar Named Desire","Tennesse Williams",""),
//                new Book("Don Quixote","Cervantes",""),
//                new Book("Anna Karenina","Leo Tolstoy",""),
//                new Book("The Mourning Bride","William Congreve",""),
//                new Book("Gone with the Wind","Margaret Mitchell",""),
//                new Book("Nancy Drew 33: The Witch Tree Symbol","Carolyn Keene",""),
//                new Book("Who's Afraid of Virginia Woolf?","Edward Albee",""),
//                new Book("The Bell Jar","Sylvia Plath",""),
//                new Book("The Art of Eating","MFK Fisher",""),
//                new Book("Glengarry Glen Ross","David Mamet",""),
//                new Book("Beyond Good and Evil","Friedrich Nietzsche",""),
//                new Book("Hamlet","William Shakespeare",""),
//                new Book("To Kill a Mockingbird","Harper Lee",""),
//                new Book("The Grapes of Wrath","John Steinbeck",""),
//                new Book("Ulysses","James Joyce",""),
//                new Book("Out of Africa","Isac Denison",""),
//                new Book("The Art of Fiction","Henry James",""),
//                new Book("My First Summer in the Sierra","John Muir",""),
//                new Book("Walden","Henry David Thoreau",""),
//                new Book("Cujo","Stephen King",""),
//                new Book("Mencken's Chrestomathy","H.R. Mencken",""),
//                new Book("Secrets of the Flesh: A Life of Colette","Judith Thurman",""),
//                new Book("A Connecticut Yankee in King Arthur's Court","Mark Twain",""),
//                new Book("Elmer Gantry","Sinclair Lewis",""),
//                new Book("Alice in Wonderland","Lewis Carroll",""),
//                new Book("Tuesdays with Morrie","Mitch Album",""),
//                new Book("Who Moved My Cheese?","Spencer Johnson",""),
//                new Book("The Meditations","Marcus Aurelius",""),
//                new Book("Mrs. Dalloway","Virginia Woolf",""),
//                new Book("Howl","Allen Ginsberg",""),
//                new Book("Oliver Twist","Charles Dickens",""),
//                new Book("Rita Hayworth and The Shawshank Redemption","Stephen King",""),
//                new Book("Selected Letters of Dawn Powell: 1913- 1965","Dawn Powell",""),
//                new Book("The Godfather: Book 1","Mario Puzo",""),
//                new Book("The Mojo Collection: The Ultimate Music Companion","Jim Irvin",""),
//                new Book("The Compact Oxford English Dictionary","",""),
//                new Book("Uncle Tom's Cabin","Harriet Beecher Stowe",""),
//                new Book("Memoirs of a Dutiful Daughter","Simone de Beauvoir",""),
//                new Book("Savage Beauty: The Life of Edna St. Vincent Millay","Nancy Milford",""),
//                new Book("The Sound and the Fury","William Faulkner",""),
//                new Book("The Last Empire: Essays 1992- 2000","Gore Vidal",""),
//                new Book("The Collected Short Stories","Eudora Welty",""),
//                new Book("Snow White and Rose Red","Grimm Brothers",""),
//                new Book("The Complete Poems","Anne Sexton",""),
//                new Book("The Divine Secrets of the Ya- Ya Sisterhood","Rebecca Wells",""),
//                new Book("Moliere: A Biography","Hobart Chatfield Taylor",""),
//                new Book("The Catcher in the Rye","J.D. Salinger",""),
//                new Book("Romeo and Juliet","William Shakespeare",""),
//                new Book("The Tragedy of Richard III","William Shakespeare",""),
//                new Book("The Iliad","Homer",""),
//                new Book("The Joy Luck Club","Amy Tan",""),
//                new Book("Gone with the Wind","Margaret Mitchell",""),
//                new Book("Nancy Drew Mysteries","Carolyn Keene",""),
//                new Book("Helter Skelter: The True Story of the Manson Murders","Vincent Bugliosi and Curt Gentry",""),
//                new Book("Biographies of Winston Churchill","",""),
//                new Book("The Scarecrow of Oz","L. Frank Baum",""),
//                new Book("The Children's Hour","Lillian Hellman",""),
//                new Book("The Fountainhead","Ayn Rand",""),
//                new Book("To Have and Have Not","Ernest Hemingway",""),
//                new Book("Franny and Zooey","J.D. Salinger",""),
//                new Book("Howl","Allen Ginsburg",""),
//                new Book("Inherit the Wind","Jerome Lawrence and Robert E. Lee",""),
//                new Book("Letters to a Young Poet","Rainer Maria Rilke",""),
//                new Book("Notes of a Dirty Old Man","Charles Bukowski",""),
//                new Book("The Return of the King: Lord of the Rings- Book 3","J.R.R. Tolkien",""),
//                new Book("Driving Miss Daisy","Alfred Uhrv",""),
//                new Book("Frankenstein","Mary Shelley",""),
//                new Book("David and Lisa","Dr. Theodore Issac Rubin, M.D.",""),
//                new Book("Memoirs of General W. T. Sherman","William Tecumseh Sherman",""),
//                new Book("Rapunzel","Brothers Grimm",""),
//                new Book("Candide","Voltaire",""),
//                new Book("The Bhagavad Gita","",""),
//                new Book("The Dirt: Confessions of the World's Most Notorious Rock Band","Tommy Lee, Vince Neil, Mick Mars and Nikki Sixx",""),
//                new Book("Waiting for Godot","Samuel Beckett",""),
//                new Book("Dr. Dolittle","Hugh Lofting",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","",""),
//                new Book("","","")
//
//
//
//
//                );
//    }
//
//
//    // generate a handful of posts, and randomly assign a user to each one
////    private void seedPosts(List<User> users) {
////        Post longPost = new Post(
////
////        );
////        List<Post> posts = Arrays.asList(
////                new Post("Title 1", "Body 1"),
////                new Post("Title 2", "Body 2"),
////                new Post("Title 3", "Body 3"),
////                new Post("Title 4", "Body 4"),
////                new Post("Example 2", "QWE Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci atque commodi eligendi necessitatibus voluptates. At distinctio dolores minus molestiae mollitia nemo sapiente ut veniam voluptates! Corporis distinctio error quaerat vel!"),
////                new Post("Example 3", "ASD Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci atque commodi eligendi necessitatibus voluptates. At distinctio dolores minus molestiae mollitia nemo sapiente ut veniam voluptates! Corporis distinctio error quaerat vel!"),
////                longPost
////        );
////        Random r = new Random();
////        for (Post p : posts) {
////            User randomUser = users.get(r.nextInt(users.size()));
////            p.setUser(randomUser);
////        }
////        postDao.save(posts);
////    }
//
////    @Override
////    public void run(String... strings) throws Exception {
////        if (! environment.equals("development")) {
////            log.info("app.env is not development, doing nothing.");
////            return;
////        }
////        log.info("Deleting posts...");
////        postDao.deleteAll();
////        log.info("Deleting users...");
////        userDao.deleteAll();
////        log.info("Seeding users...");
////        List<User> users = seedUsers();
////        log.info("Seeding posts...");
////        seedPosts(users);
////        log.info("Finished running seeders!");
////    }
//}