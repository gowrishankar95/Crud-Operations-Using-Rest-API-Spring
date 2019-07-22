package com.hms.mangement.UserManagement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserJDBCTemplate implements UserDAO {

    private JdbcTemplate jdbcTemplate;
   // private DataSource dataSource;

    /*

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;

    }
*/
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }



    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (userName,passowrd) VALUES (?, ?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword());

    }



    @Override
    public String deleteUser(int id) {
        User user = isIdExist(id);
        if (user == null) {
            System.out.println("id not found");
            return "id not found";
        } else {
            String sql = "DELETE  FROM users  WHERE UserId=?";
            jdbcTemplate.update(sql,id);
            System.out.println("user id "+ id +" deleted");
            return "user id "+ id +" deleted";

        }


    }

    @Override
    public List<User> viewAllUsers() {

        String sql = "SELECT UserId, userName, passowrd" + " FROM users";
        List<User> users = jdbcTemplate.query(sql,new UserMapper());
        return users;
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
            System.out.println("id not found");
            return "id not found";
        }
        else if (!user.getPassword().equals(oldPassword)) {
            return "invalid password";
        } else {
            String sql = "UPDATE users SET passowrd=? WHERE UserId=?";
            jdbcTemplate.update(sql,newPassword,id);
            System.out.println("record updated");
            return "update success";

        }
    }
}
