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

    private static final String GET_STUDENT = "SELECT s.* FROM students s WHERE s.id = ?";

    private static final String GET_STUDENT_LIST = "SELECT s.* FROM students s;";

    private static final String GET_STUDENT_LIST_IS_NOT_LESSON = "SELECT * FROM  students s  WHERE s.id  " +
            "NOT IN (SELECT std.id_student FROM lesson_std std WHERE std.id_lesson = ?);";

    private static final String UPDATE_STUDENT = "UPDATE students SET surname=?,name=?,gender=?,birthday=? WHERE id = ?";

    private static final String INSERT_STUDENT = "INSERT INTO students (surname, name,gender,birthday) VALUES (?, ?,?,?)";

    private static final String DELETE_STUDENT = "DELETE FROM lesson_std WHERE id_student = ?; " +
            "DELETE FROM students WHERE id = ?";


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Получение студента
     * @param id студента
     * @return студент
     */
    @Override
    public Student getStudent(Integer id) {
        Student student = this.jdbcTemplate.queryForObject(GET_STUDENT, new Object[]{id},
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

    /**
     * Получение списка студента
     * @return список студентов
     */
    @Override
    public List<Student> getStudentList() {
        List<Student> students = this.jdbcTemplate.query(GET_STUDENT_LIST,
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

    /**
     * Получение списка студентов не назначенных выбранному занятию
     * @param idLesson занятие
     * @return список студентов
     */
    @Override
    public List<Student> getStudentListIsNotLesson(Integer idLesson) {
        List<Student> students = this.jdbcTemplate.query(GET_STUDENT_LIST_IS_NOT_LESSON,
                (rs, rowNum) -> {
                    Student std = new Student();
                    std.setId(rs.getInt("id"));
                    std.setSurname(rs.getString("surname"));
                    std.setName(rs.getString("name"));
                    std.setGender(rs.getString("gender"));
                    std.setBirthday(rs.getDate("birthday"));
                    return std;
                }, idLesson);
        return students;
    }

    /**
     * Добавление/Редактирование студента
     * @param std студент
     */
    @Override
    public void addStudent(Student std) {
        if (std.getId() != null) {
            this.jdbcTemplate.update(UPDATE_STUDENT, std.getSurname(), std.getName(),
                    std.getGender(), std.getBirthday(), std.getId());
        } else {
            this.jdbcTemplate.update(INSERT_STUDENT, std.getSurname(), std.getName(),
                    std.getGender(), std.getBirthday());
        }
    }

    /**
     * Удаление студента
     * @param id студента
     */
    @Override
    public void deleteStudent(Integer id) {
        this.jdbcTemplate.update(DELETE_STUDENT, id, id);
    }


}
