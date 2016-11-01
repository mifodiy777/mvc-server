package ru.innopolis.mvc.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.mvc.DAO.StudentDAO;
import ru.innopolis.mvc.entity.Student;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
@Repository
public class StudentDAOImpl implements StudentDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Student getStudent(Integer id) {
        Student student = this.jdbcTemplate.queryForObject(
                "SELECT s.* FROM students s WHERE s.id = ?", new Object[]{id},
                (rs, rowNum) -> {
                    Student std = new Student();
                    std.setId(rs.getInt("id"));
                    std.setSurname(rs.getString("surname"));
                    std.setName(rs.getString("name"));
                    std.setGender(rs.getString("gender"));
                    std.setBirthday(rs.getDate("birthday"));
                    return std;
                });
        return student;
    }

    @Override
    public List<Student> getStudentList() {
        List<Student> students = this.jdbcTemplate.query(
                "SELECT s.* FROM students s",
                (rs, rowNum) -> {
                    Student std = new Student();
                    std.setId(rs.getInt("id"));
                    std.setSurname(rs.getString("surname"));
                    std.setName(rs.getString("name"));
                    std.setGender(rs.getString("gender"));
                    std.setBirthday(rs.getDate("birthday"));
                    return std;
                });
        return students;
    }

    @Override
    public void addStudent(Student std) {
        if (std.getId() != null) {
            this.jdbcTemplate.update(
                    "UPDATE students SET surname=?,name=?,gender=?,birthday=? WHERE id = ?",
                    std.getSurname(), std.getName(), std.getGender(), std.getBirthday(), std.getId());
        } else {
            this.jdbcTemplate.update("INSERT INTO students (surname, name,gender,birthday) VALUES (?, ?,?,?)",
                    std.getSurname(), std.getName(), std.getGender(), std.getBirthday());
        }
    }

    @Override
    public void deleteStudent(Integer id) {
        this.jdbcTemplate.update("DELETE FROM lesson_std WHERE id_student = ?", id);
        this.jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }


}
