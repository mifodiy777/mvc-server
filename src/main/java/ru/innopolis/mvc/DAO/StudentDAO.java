package ru.innopolis.mvc.DAO;

import ru.innopolis.mvc.entity.Student;

import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
public interface StudentDAO {

    Student getStudent(Integer id);

    List<Student> getStudentList();

    void addStudent(Student student);

    void deleteStudent(Integer id);

}
