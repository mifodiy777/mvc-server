package ru.innopolis.mvc.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.innopolis.mvc.DAO.StudentDAO;
import ru.innopolis.mvc.entity.Student;
import ru.innopolis.mvc.entityModal.StudentModal;
import ru.innopolis.mvc.exception.DataSQLException;
import ru.innopolis.mvc.service.StudentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private StudentDAO studentDAO;

    /**
     * Получение студента
     *
     * @param id студента
     * @return Экзепляр studentModal;
     * @throws DataSQLException
     */
    @Override
    public StudentModal getStudent(Integer id) throws DataSQLException {
        try {
            return mapper.map(studentDAO.findOne(id), StudentModal.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получения определенного студента");
        }

    }

    /**
     * Получение всех студентов из базы
     *
     * @return список логической сущности Student
     * @throws DataSQLException
     */
    @Override
    public List<StudentModal> getStudentList() throws DataSQLException {
        List<StudentModal> studentModals = new ArrayList<>();
        try {
            mapper.mapAsCollection(studentDAO.findAll(), studentModals, StudentModal.class);
            return studentModals;
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка получения всех студентов");
        }
    }

    /**
     * Получение списка студентов не записанных на определенное занятие
     *
     * @param idLesson занятия
     * @return список логической сущности Student
     * @throws DataSQLException
     */
    @Override
    public List<StudentModal> getStudentListIsNotLesson(Integer idLesson) throws DataSQLException {
        List<StudentModal> studentModals = new ArrayList<>();
        try {
            mapper.mapAsCollection(studentDAO.getStudentListIsNotLesson(idLesson), studentModals, StudentModal.class);
            return studentModals;
        } catch (SQLException e) {
            throw new DataSQLException("Ошибка получения списка студентов не записанных на определенное занятие");
        }
    }

    /**
     * Сохранение студента
     *
     * @param studentModal логический объект Student
     * @throws DataSQLException
     */
    @Override
    public void saveStudent(StudentModal studentModal) throws DataSQLException {
        try {
            Student student = mapper.map(studentModal, Student.class);
            studentDAO.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка сохранения студента");
        }
    }

    /**
     * Удаление студента
     *
     * @param id студента
     * @throws DataSQLException
     */
    @Override
    public void deleteStudent(Integer id) throws DataSQLException {
        try {
            studentDAO.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataSQLException("Ошибка удаления студента");
        }
    }

    /**
     * Получение кол-ва занятий посетившие определенный студент
     *
     * @param studentId студент id
     * @return кол-во занятий
     */
    @Override
    public Integer countLesson(Integer studentId) throws DataSQLException {
        try {
            return studentDAO.getCountLesson(studentId);
        } catch (SQLException e) {
            throw new DataSQLException("Ошибка получения количества занятий посетившим определенным студентом");
        }
    }

}
