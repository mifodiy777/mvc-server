package ru.innopolis.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.mvc.DAO.LessonDAO;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.service.LessonService;

import java.util.List;

/**
 * Created by Кирилл on 01.11.2016.
 */
@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDAO lessonDAO;

    @Override
    public Lesson getLesson(Integer id) {
        return lessonDAO.getLesson(id);
    }

    @Override
    public List<Lesson> getLessonList() {
        return lessonDAO.getLessonList();
    }

    @Override
    public void addLesson(Lesson lesson) {
        lessonDAO.addLesson(lesson);
    }

    @Override
    public void deleteLesson(Integer id) {
        lessonDAO.deleteLesson(id);
    }

    @Override
    public void putStudent(Integer idLesson, Integer idStudent) {
        lessonDAO.putLesson(idLesson, idStudent);
    }
}
