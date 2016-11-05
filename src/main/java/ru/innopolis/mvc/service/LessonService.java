package ru.innopolis.mvc.service;

import ru.innopolis.mvc.entity.Lesson;

import java.util.List;

/**
 * Created by Кирилл on 01.11.2016.
 */
public interface LessonService {

    Lesson getLesson(Integer id);

    List<Lesson> getLessonList();

    void addLesson(Lesson lesson);

    void deleteLesson(Integer id);

    void putStudent(Integer idLesson, Integer idStudent);

}
