package ru.innopolis.mvc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entity.Student;

import java.util.List;

/**
 * Created by Кирилл on 01.11.2016.
 */
@Repository
public interface LessonDAO extends JpaRepository<Lesson, Integer> {

}
