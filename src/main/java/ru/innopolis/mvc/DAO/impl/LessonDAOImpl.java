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

    private JdbcTemplate jdbcTemplate;

    private static final String GET_LESSON = "SELECT l.*, s.id AS id_student, s.surname,s.name FROM lessons l " +
            "LEFT JOIN lesson_std sl ON l.id=sl.id_lesson " +
            "LEFT JOIN students s ON s.id=sl.id_student " +
            "WHERE l.id = ?";

    private static final String GET_LESSON_LIST = "SELECT l.* FROM lessons l";

    private static final String GET_LESSON_LIST_ON_STUDENT = "SELECT l.* FROM lessons l " +
            "LEFT JOIN lesson_std sl ON l.id=sl.id_lesson " +
            "LEFT JOIN students s ON s.id=sl.id_student " +
            "WHERE s.id = ?";

    private static final String UPDATE_LESSON = "UPDATE lessons SET topic=?,description=?,duration=?,date_lesson=? WHERE id = ?";

    private static final String INSERT_LESSON = "INSERT INTO lessons (topic,description, duration,date_lesson) VALUES (?, ?,?,?)";

    private static final String DELETE_LESSON = "DELETE FROM lesson_std WHERE id_lesson = ?; DELETE FROM lessons WHERE id = ?";

    private static final String PUT_LESSON = "INSERT INTO lesson_std (id_lesson, id_student) VALUES (?, ?)";

    @Autowired

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Получение занятия
     * @param id занятия
     * @return занятие
     */
    @Override
    public Lesson getLesson(Integer id) {
        Set<Student> students = new HashSet<>();
        List<Lesson> lessons = this.jdbcTemplate.query(GET_LESSON,
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
        lesson.setStudents(students);
        return lesson;
    }

    /**
     * Получение коллекции занятий из базы
     * @return список занятий
     */
    @Override
    public List<Lesson> getLessonList() {
        return this.jdbcTemplate.query(GET_LESSON_LIST,
                (rs, rowNum) -> {
                    Lesson ls = new Lesson();
                    ls.setId(rs.getInt("id"));
                    ls.setTopic(rs.getString("topic"));
                    ls.setDescription(rs.getString("description"));
                    ls.setDuration(rs.getInt("duration"));
                    ls.setDateLesson(rs.getDate("date_lesson"));
                    return ls;
                });
    }

    /**
     * Получение списка занятий определенного студента
     * @param id студента
     * @return список занятий
     */
    @Override
    public List<Lesson> getLessonListOnStudent(Integer id) {
        return this.jdbcTemplate.query(GET_LESSON_LIST_ON_STUDENT,
                (rs, rowNum) -> {
                    Lesson ls = new Lesson();
                    ls.setId(rs.getInt("id"));
                    ls.setTopic(rs.getString("topic"));
                    ls.setDescription(rs.getString("description"));
                    ls.setDuration(rs.getInt("duration"));
                    ls.setDateLesson(rs.getDate("date_lesson"));
                    return ls;
                }, id);

    }

    /**
     * Добавление/Редактирование занятия
     * @param lesson занятие
     */
    @Override
    public void addLesson(Lesson lesson) {
        if (lesson.getId() != null) {
            this.jdbcTemplate.update(UPDATE_LESSON,
                    lesson.getTopic(), lesson.getDescription(), lesson.getDuration(), lesson.getDateLesson(), lesson.getId());
        } else {
            this.jdbcTemplate.update(INSERT_LESSON,
                    lesson.getTopic(), lesson.getDescription(), lesson.getDuration(), lesson.getDateLesson());
        }
    }

    /**
     * Удаление занятия
     * @param id занятия
     */
    @Override
    public void deleteLesson(Integer id) {
        this.jdbcTemplate.update(DELETE_LESSON, id, id);
    }

    /**
     * Добавление студента к занятию
     * @param idLesson занятие
     * @param idStudent студент
     */
    @Override
    public void putLesson(Integer idLesson, Integer idStudent) {
        try {
            this.jdbcTemplate.update(PUT_LESSON, idLesson, idStudent);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
}
