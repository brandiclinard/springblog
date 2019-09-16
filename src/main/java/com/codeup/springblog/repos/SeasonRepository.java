package com.codeup.springblog.repos;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.models.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeasonRepository extends CrudRepository<Season, Long> {


}
