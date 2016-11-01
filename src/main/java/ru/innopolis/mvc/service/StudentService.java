package ru.innopolis.mvc.service;

import ru.innopolis.mvc.entity.Student;

import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
public interface StudentService {

    Student getStudent(Integer id);

    List<Student> getStudentList();

    List<Student> getStudentListIsNotLesson(Integer idLesson);

    void saveStudent(Student student);

    void deleteStudent(Integer id);

    Integer countLesson(Integer id);
}
