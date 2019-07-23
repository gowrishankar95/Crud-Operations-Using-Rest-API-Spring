package com.hms.mangement.UserManagement;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO  {

    public void create(User user);

    public String updatePassword(int UserId, String oldPassword, String newPassword);

    public String deleteUser(int id);

    public List<User> viewAllUsers();
}
