package com.codeup.springblog.repos;

import com.codeup.springblog.models.Status;
import com.codeup.springblog.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Long> {

//Status findAllByUser(User user);

    List<Status> findByName(String name);

}
