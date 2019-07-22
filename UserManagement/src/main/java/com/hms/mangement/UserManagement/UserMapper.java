package com.hms.mangement.UserManagement;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("UserId"));
        user.setUsername(resultSet.getString("userName"));
        user.setPassword(resultSet.getString("passowrd"));

        return user;
    }
}
