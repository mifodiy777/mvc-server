package ru.innopolis.mvc.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.mvc.entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Кирилл on 31.10.2016.
 */
@Repository
public interface StudentDAO extends CrudRepository<Student, Integer> {

    List<Student> findByLesson (Integer idLesson) throws SQLException;

}
