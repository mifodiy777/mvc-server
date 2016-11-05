package ru.innopolis.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.mvc.DAO.LessonDAO;
import ru.innopolis.mvc.DAO.StudentDAO;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entity.Student;
import ru.innopolis.mvc.service.StudentService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Кирилл on 31.10.2016.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public Student getStudent(Integer id) {
        return studentDAO.findOne(id);
    }

    @Override
    public List<Student> getStudentList() {
        return studentDAO.findAll();
    }

    /**
     * Получение списка студентов не записанных на определенное занятие
     * @param idLesson
     * @return
     */
    @Override
    public List<Student> getStudentListIsNotLesson(Integer idLesson) {
        return studentDAO.getStudentListIsNotLesson(idLesson);
    }

    @Override
    public void saveStudent(Student student) {
        studentDAO.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentDAO.delete(id);
    }

    /**
     * Получение кол-ва занятий посетившие определенный студент
     * @param studentId
     * @return кол-во занятий
     */
    @Override
    public Integer countLesson(Integer studentId) {
        return studentDAO.getCountLesson(studentId);
    }

}
