package ru.innopolis.mvc.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.mvc.DAO.LessonDAO;
import ru.innopolis.mvc.DAO.StudentDAO;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entity.Student;
import ru.innopolis.mvc.entityModal.LessonModal;
import ru.innopolis.mvc.entityModal.StudentModal;
import ru.innopolis.mvc.service.LessonService;

import java.util.ArrayList;
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

    @Autowired
    private MapperFacade mapper;

    @Override
    public LessonModal getLesson(Integer id) {
        return mapper.map(lessonDAO.findOne(id), LessonModal.class);
    }

    @Override
    public List<LessonModal> getLessonList() {
        List<LessonModal> lessonDTOList = new ArrayList<>();
        mapper.mapAsCollection(lessonDAO.findAll(), lessonDTOList, LessonModal.class);
        return lessonDTOList;

    }

    /**
     * Получение занятия из базы с существующими студентами и запись этих студентов в отредактированное занятие
     *
     * @param lessonModal
     */
    @Override
    @Transactional
    public void addLesson(LessonModal lessonModal) {
        if (lessonModal.getId() != null) {
            LessonModal old = mapper.map(lessonDAO.findOne(lessonModal.getId()), LessonModal.class);
            lessonModal.setStudents(old.getStudents());
        }
        lessonDAO.save(mapper.map(lessonModal, Lesson.class));
    }

    @Override
    @Transactional
    public void deleteLesson(Integer id) {
        lessonDAO.delete(id);
    }

    /**
     * Запись студента на занятие
     *
     * @param idLesson
     * @param idStudent
     */
    @Override
    public void putStudent(Integer idLesson, Integer idStudent) {
        LessonModal lesson = mapper.map(lessonDAO.findOne(idLesson), LessonModal.class);
        StudentModal student = mapper.map(studentDAO.findOne(idStudent), StudentModal.class);
        lesson.getStudents().add(student);
        lessonDAO.save(mapper.map(lesson, Lesson.class));
    }
}
