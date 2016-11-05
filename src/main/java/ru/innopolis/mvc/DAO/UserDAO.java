package ru.innopolis.mvc.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.mvc.entity.Lesson;
import ru.innopolis.mvc.entity.User;

/**
 * Created by Кирилл on 02.11.2016.
 */
@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

    User findByLogin(String login);
}
