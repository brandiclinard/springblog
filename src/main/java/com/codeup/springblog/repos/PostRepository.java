package com.codeup.springblog.repos;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {// use model type and type for id

    //full crud functionality easily accessible here.



    Post findByTitle (String title);

    @Query("from Post p where p.title like %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);






}
