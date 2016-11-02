package ru.innopolis.mvc.DAO.impl;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.mvc.DAO.LessonDAO;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entity.Student;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by Кирилл on 01.11.2016.
 */
@Repository
public class LessonDAOImpl implements LessonDAO {

   /* private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }*/

    @Override
    public Lesson getLesson(Integer id) {
       /* String sql = "SELECT l.*, s.id AS id_student, s.surname,s.name FROM lessons l " +
                "LEFT JOIN lesson_std sl ON l.id=sl.id_lesson " +
                "LEFT JOIN students s ON s.id=sl.id_student " +
                "WHERE l.id = ?";
        Set<Student> students = new HashSet<>();

        List<Lesson> lessons = this.jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Lesson ls = new Lesson();
                    ls.setId(rs.getInt("id"));
                    ls.setTopic(rs.getString("topic"));
                    ls.setDescription(rs.getString("description"));
                    ls.setDuration(rs.getInt("duration"));
                    ls.setDateLesson(rs.getDate("date_lesson"));
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setSurname(rs.getString("surname"));
                    student.setName(rs.getString("name"));
                    students.add(student);
                    return ls;
                }, id);
        Lesson lesson = lessons.get(0);
        lesson.setStudents(students);*/
        return null;
    }

    @Override
    public List<Lesson> getLessonList() {
        return /*this.jdbcTemplate.query(
                "SELECT l.* FROM lessons l",
                (rs, rowNum) -> {
                    Lesson ls = new Lesson();
                    ls.setId(rs.getInt("id"));
                    ls.setTopic(rs.getString("topic"));
                    ls.setDescription(rs.getString("description"));
                    ls.setDuration(rs.getInt("duration"));
                    ls.setDateLesson(rs.getDate("date_lesson"));
                    return ls;
                });*/null;
    }

    @Override
    public List<Lesson> getLessonListOnStudent(Integer id) {
        return /*this.jdbcTemplate.query(
                "SELECT l.* FROM lessons l " +
                        "LEFT JOIN lesson_std sl ON l.id=sl.id_lesson " +
                        "LEFT JOIN students s ON s.id=sl.id_student " +
                        "WHERE s.id = ?",
                (rs, rowNum) -> {
                    Lesson ls = new Lesson();
                    ls.setId(rs.getInt("id"));
                    ls.setTopic(rs.getString("topic"));
                    ls.setDescription(rs.getString("description"));
                    ls.setDuration(rs.getInt("duration"));
                    ls.setDateLesson(rs.getDate("date_lesson"));
                    return ls;
                }, id);*/ null;

    }

    @Override
    public void addLesson(Lesson lesson) {
       /* if (lesson.getId() != null) {
            this.jdbcTemplate.update(
                    "UPDATE lessons SET topic=?,description=?,duration=?,date_lesson=? WHERE id = ?",
                    lesson.getTopic(), lesson.getDescription(), lesson.getDuration(), lesson.getDateLesson(), lesson.getId());
        } else {
            this.jdbcTemplate.update("INSERT INTO lessons (topic,description, duration,date_lesson) VALUES (?, ?,?,?)",
                    lesson.getTopic(), lesson.getDescription(), lesson.getDuration(), lesson.getDateLesson());
        }*/
    }

    @Override
    public void deleteLesson(Integer id) {
      /*  this.jdbcTemplate.update("DELETE FROM lesson_std WHERE id_lesson = ?", id);
        this.jdbcTemplate.update("DELETE FROM lessons WHERE id = ?", id);*/
    }

    @Override
    public void putLesson(Integer idLesson, Integer idStudent) {
       /* try {
            this.jdbcTemplate.update("INSERT INTO lesson_std (id_lesson, id_student) VALUES (?, ?)",
                    idLesson, idStudent);
        } catch (DataAccessException e){
            e.printStackTrace();
        }*/

    }
}
