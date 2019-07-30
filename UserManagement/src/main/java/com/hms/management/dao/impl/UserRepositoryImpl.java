package com.hms.management.dao.impl;

import com.hms.management.User;
import com.hms.management.UserMapper;
import com.hms.management.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User create(User user) {
        String sql = "INSERT INTO users (userName,passowrd) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"UserId"});
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                return ps;
            }
        }, keyHolder);
        user.setUserId( Integer.parseInt(keyHolder.getKey()+""));
        logger.info("successfully created user");
        return user;

    }

    @Override
    public User deleteUser(int id) {
        User user = isIdExist(id);
        if (user == null) {
            logger.warn("User with id {} not found", id );
            return null;
        } else {
            String sql = "DELETE  FROM users  WHERE UserId=?";
            jdbcTemplate.update(sql,id);
            System.out.println("user id "+ id +" deleted");
            logger.info("user succesfully deleted");
            return user;

        }
    }

    @Override
    public List<User> viewAllUsers() {

        String sql = "SELECT UserId, userName, passowrd" + " FROM users";
        List<User> users = jdbcTemplate.query(sql,new UserMapper());
        return users;
    }

    @Override
    public User getUser(int userId) {
        String sql = "SELECT * "
                + "FROM users" + " where UserId = " + "\'" + userId + "\'";
        List<User> user = jdbcTemplate.query(sql, new UserMapper());
        if(user == null){
            logger.warn("user with user id {} does not exist", userId);
            return null;
        }
        else {
            logger.warn("returning user");
            return user.get(0);
        }

    }

    public User isIdExist(int userId) {
        String sql = "SELECT * "
                + "FROM users" + " where UserId = " + "\'" + userId + "\'";
        List<User> user = jdbcTemplate.query(sql, new UserMapper());
        if (user.isEmpty()) {
            return null;
        } else {
            return user.get(0);
        }
    }
    @Override
    public String updatePassword(int id, String oldPassword, String newPassword) {
        User user = isIdExist(id);
        if (user == null) {
            logger.warn("User with id {} not found", id);
            return "User with id " + id +" not found";
        }
        else if (!user.getPassword().equals(oldPassword)) {
            return "invalid password";
        } else {
            String sql = "UPDATE users SET passowrd=? WHERE UserId=?";
            jdbcTemplate.update(sql,newPassword,id);
            logger.info("User Id {} password succesfully updated", id);
            return "User Id " + id +" password succesfully updated";

        }
    }
}
