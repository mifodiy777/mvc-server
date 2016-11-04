package ru.innopolis.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.innopolis.mvc.DAO.UserDAO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Кирилл on 02.11.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       ru.innopolis.mvc.entity.User user = userDAO.findByLogin(username.trim());
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с таким логином не найден");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        String fio = user.getSurname() + " " + user.getName();
        User securityUser = new User(fio.trim(), user.getPassword(), true, true, true, true, authorities);

        return securityUser;
    }
}