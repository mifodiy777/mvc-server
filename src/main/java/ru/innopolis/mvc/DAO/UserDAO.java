package ru.innopolis.mvc.DAO;

import ru.innopolis.mvc.entity.User;

/**
 * Created by Кирилл on 02.11.2016.
 */
public interface UserDAO {

    User findByLogin(String login);
}
