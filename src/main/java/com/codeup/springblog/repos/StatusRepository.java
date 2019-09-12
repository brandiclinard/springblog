package com.codeup.springblog.repos;

import com.codeup.springblog.models.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {


}
