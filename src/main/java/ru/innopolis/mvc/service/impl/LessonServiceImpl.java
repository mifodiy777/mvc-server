package ru.innopolis.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.mvc.DAO.LessonDAO;
import ru.innopolis.mvc.DAO.StudentDAO;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entity.Student;
import ru.innopolis.mvc.service.LessonService;

import java.util.List;

/**
 * Created by Кирилл on 01.11.2016.
 */
@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDAO lessonDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public Lesson getLesson(Integer id) {
        return lessonDAO.findOne(id);
    }

    @Override
    public List<Lesson> getLessonList() {
        return lessonDAO.findAll();
    }

    /**
     * Получение занятия из базы с существующими студентами и запись этих студентов в отредактированное занятие
     * @param lesson
     */
    @Override
    @Transactional
    public void addLesson(Lesson lesson) {
        Lesson old = lessonDAO.findOne(lesson.getId());
        lesson.setStudents(old.getStudents());
        lessonDAO.save(lesson);
    }

    @Override
    @Transactional
    public void deleteLesson(Integer id) {
        lessonDAO.delete(id);
    }

    /**
     * Запись студента на занятие
     * @param idLesson
     * @param idStudent
     */
    @Override
    public void putStudent(Integer idLesson, Integer idStudent) {
        Lesson lesson = lessonDAO.findOne(idLesson);
        Student student = studentDAO.findOne(idStudent);
        lesson.getStudents().add(student);
        lessonDAO.save(lesson);
    }
}
