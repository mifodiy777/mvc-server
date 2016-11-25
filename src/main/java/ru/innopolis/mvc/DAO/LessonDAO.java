package ru.innopolis.mvc.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.mvc.entity.Lesson;

/**
 * Created by Кирилл on 01.11.2016.
 */
@Repository
public interface LessonDAO extends CrudRepository<Lesson, Integer> {

}
