package ru.innopolis.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.mvc.DAO.LessonDAO;
import ru.innopolis.mvc.DAO.StudentDAO;
import ru.innopolis.mvc.entity.Student;
import ru.innopolis.mvc.service.StudentService;

import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private LessonDAO lessonDAO;

    @Override
    public Student getStudent(Integer id) {
        return studentDAO.getStudent(id);
    }

    @Override
    public List<Student> getStudentList() {
        return studentDAO.getStudentList();
    }

    @Override
    public List<Student> getStudentListIsNotLesson(Integer idLesson) {
       return studentDAO.getStudentListIsNotLesson(idLesson);
    }

    @Override
    public void saveStudent(Student student) {
        studentDAO.addStudent(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentDAO.deleteStudent(id);
    }

    @Override
    public Integer countLesson(Integer id) {
       return lessonDAO.getLessonListOnStudent(id).size();
    }
}
