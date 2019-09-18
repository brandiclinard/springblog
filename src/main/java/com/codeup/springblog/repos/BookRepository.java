package com.codeup.springblog.repos;

import com.codeup.springblog.models.*;
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

    @Query(value= "select * from spring_project_db.books b join spring_project_db.statuses s on s.book_id = b.id join Users u on u.id = s.user_id where s.name = 'current' and s.user_id = ?", nativeQuery = true)
    List<Book> currentBooksByUserId(long id);

    @Query(value= "select * from spring_project_db.books b join spring_project_db.statuses s on s.book_id = b.id join Users u on u.id = s.user_id where s.name = 'wish' and s.user_id = ?", nativeQuery = true)
    List<Book> wishBooksByUserId(long id);

    @Query(value= "select * from spring_project_db.books b join spring_project_db.statuses s on s.book_id = b.id join Users u on u.id = s.user_id where s.name = 'complete' and s.user_id = ?", nativeQuery = true)
    List<Book> completeBooksByUserId(long id);



}