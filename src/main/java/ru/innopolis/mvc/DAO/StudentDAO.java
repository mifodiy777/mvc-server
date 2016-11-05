package ru.innopolis.mvc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entity.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by Кирилл on 31.10.2016.
 */
@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {


    @Query(value = "SELECT * FROM  student s  WHERE s.id  NOT IN (SELECT std.student_id FROM lesson_std std WHERE std.lesson_id = ?1)", nativeQuery = true)
    List<Student> getStudentListIsNotLesson(Integer idLesson);

    @Query("select count(l) FROM Lesson as l inner join l.students s where s.id = ?1")
    Integer getCountLesson(Integer studentId);
}
