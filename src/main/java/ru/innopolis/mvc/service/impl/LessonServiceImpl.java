package ru.innopolis.mvc.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.common.exception.DataSQLException;
import ru.innopolis.common.modal.LessonModal;
import ru.innopolis.common.modal.StudentModal;
import ru.innopolis.common.service.LessonService;
import ru.innopolis.mvc.DAO.LessonDAO;
import ru.innopolis.mvc.DAO.StudentDAO;
import ru.innopolis.mvc.entity.Lesson;

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

    /**
     * Получение определенного занятия
     *
     * @param id занятия
     * @return Экземпляр Lesson
     * @throws DataSQLException
     */
    @Override
    @Transactional
    public LessonModal getLesson(Integer id) throws DataSQLException {
        try {
            return mapper.map(lessonDAO.findOne(id), LessonModal.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получения определенного занятия");
        }
    }

    /**
     * Получение всех занятий
     *
     * @return список логической сущности LessonModal
     * @throws DataSQLException
     */
    @Override
    @Transactional
    public List<LessonModal> getLessonList() throws DataSQLException {
        List<LessonModal> lessonDTOList = new ArrayList<>();
        try {
            mapper.mapAsCollection(lessonDAO.findAll(), lessonDTOList, LessonModal.class);
            return lessonDTOList;
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получения всех студентов");
        }

    }

    /**
     * Получение занятия из базы с существующими студентами и запись этих студентов в отредактированное занятие
     *
     * @param lessonModal логическое представление Lesson
     * @throws DataSQLException
     */
    @Override
    public void addLesson(LessonModal lessonModal) throws DataSQLException {
        try {
            if (lessonModal.getId() != null) {
                LessonModal old = mapper.map(lessonDAO.findOne(lessonModal.getId()), LessonModal.class);
                lessonModal.setStudents(old.getStudents());
            }
            lessonDAO.save(mapper.map(lessonModal, Lesson.class));
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка сохранения занятий");
        }
    }

    /**
     * Удаление занятий
     *
     * @param id занятия
     * @throws DataSQLException
     */
    @Override
    public void deleteLesson(Integer id) throws DataSQLException {
        try {
            lessonDAO.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка удаления занятий");
        }
    }

    /**
     * Запись студента на занятие
     *
     * @param idLesson  занятия
     * @param idStudent студента
     * @throws DataSQLException
     */
    @Override
    @Transactional
    public void putStudent(Integer idLesson, Integer idStudent) throws DataSQLException {
        try {
            LessonModal lesson = mapper.map(lessonDAO.findOne(idLesson), LessonModal.class);
            StudentModal student = mapper.map(studentDAO.findOne(idStudent), StudentModal.class);
            lesson.getStudents().add(student);
            lessonDAO.save(mapper.map(lesson, Lesson.class));
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка записи студента на занятие");
        }
    }
}
