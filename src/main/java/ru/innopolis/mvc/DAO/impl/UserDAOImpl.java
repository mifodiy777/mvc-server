package ru.innopolis.mvc.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.mvc.DAO.UserDAO;
import ru.innopolis.mvc.entity.Role;
import ru.innopolis.mvc.entity.Student;
import ru.innopolis.mvc.entity.User;

import javax.sql.DataSource;

/**
 * Created by Кирилл on 02.11.2016.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static final String FIND_BY_LOGIN = "SELECT u.*,r.id AS rid, r.name AS rname FROM  user u LEFT JOIN role r ON u.id_role=r.id WHERE u.login = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Аутентификация по логину
     * @param login
     * @return User
     */
    @Override
    public User findByLogin(String login) {
        User user = this.jdbcTemplate.queryForObject(
                FIND_BY_LOGIN, new Object[]{login},
                (rs, rowNum) -> {
                    User usr = new User();
                    usr.setId(rs.getInt("id"));
                    usr.setSurname(rs.getString("surname"));
                    usr.setName(rs.getString("name"));
                    usr.setLogin(rs.getString("login"));
                    usr.setPassword(rs.getString("password"));
                    usr.setActive(true);
                    Role role = new Role();
                    role.setId(rs.getInt("rid"));
                    role.setName(rs.getString("rname"));
                    usr.setRole(role);
                    usr.setActive(rs.getBoolean("active"));
                    return usr;
                });
        return user;
    }
}
