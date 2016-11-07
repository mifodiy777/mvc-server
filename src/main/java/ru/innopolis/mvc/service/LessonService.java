package ru.innopolis.mvc.service;

import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entityModal.LessonModal;

import java.util.List;

/**
 * Created by Кирилл on 01.11.2016.
 */
public interface LessonService {

    LessonModal getLesson(Integer id);

    List<LessonModal> getLessonList();

    void addLesson(LessonModal lesson);

    void deleteLesson(Integer id);

    void putStudent(Integer idLesson, Integer idStudent);

}
