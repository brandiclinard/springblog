package com.codeup.springblog.repos;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {// use model type and type for id

    //full crud functionality easily accessible here.

//    List<Post> getAll();
//
////    @Query("from Post p where p.id like ?1")
//    Post getPostById(long id);
//
//    @Query("from Post p where p.title like %:term%")
//    List<Post> searchByTitleLike(@Param("term") String term);
//
//    boolean addPost(Post post);
//
//    void update(Post post);
//
//    void delete(long id);
//
//    void deleteAll();





}
