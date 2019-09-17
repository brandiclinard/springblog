package com.codeup.springblog.repos;

import com.codeup.springblog.models.Status;
import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Long> {

//Status findAllByUser(User user);

    List<Status> findByName(String name);

    @Query(value="select  s.id from spring_project_db.statuses s join spring_project_db.books b on s.book_id = b.id join users u on u.id = s.user_id where s.name = 'current' and s.user_id = ? and s.book_id = ?", nativeQuery = true)
    long currentStatusObjectId(long user_id, long book_id);

    @Query(value="select  s.id from spring_project_db.statuses s join spring_project_db.books b on s.book_id = b.id join users u on u.id = s.user_id where s.name = 'wish' and s.user_id = ? and s.book_id = ?", nativeQuery = true)
    long wishStatusObjectId(long user_id, long book_id);

    @Query(value="select s.id from spring_project_db.statuses s join spring_project_db.books b on s.book_id = b.id join users u on u.id = s.user_id where s.name = 'complete' and s.user_id = ? and s.book_id = ?", nativeQuery = true)
    long completeStatusObjectId(long user_id, long book_id);
}
