package com.codeup.springblog.repos;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.Season;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {// use model type and type for id

    //full crud functionality easily accessible here.


    @Query("from Book b where b.title like %:term% or b.author like %:term%")
    List<Book> searchByTitleOrAuthor(@Param("term") String term);

    List<Book> findBySeason(Season season);


}