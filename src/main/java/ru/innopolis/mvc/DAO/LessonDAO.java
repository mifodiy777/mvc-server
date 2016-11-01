package ru.innopolis.mvc.DAO;

import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entity.Student;

import java.util.List;

/**
 * Created by Кирилл on 01.11.2016.
 */
public interface LessonDAO {

    Lesson getLesson(Integer id);

    List<Lesson> getLessonList();

    List<Lesson> getLessonListOnStudent(Integer id);

    void addLesson(Lesson lesson);

    void deleteLesson(Integer id);

    void putLesson(Integer idLesson, Integer idStudent);
}
