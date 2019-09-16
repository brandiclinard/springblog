package com.codeup.springblog.repos;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {// use model type and type for id

    //full crud functionality easily accessible here.



//    Post findByTitle (String title);
//
//    @Query("from Post p where p.title like %:term%")
//    List<Post> searchByTitleLike(@Param("term") String term);


    List<Post> findByBook(Book book);

    List<Post> findByUserAndBook(User user, Book book);



}
