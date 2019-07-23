package com.hms.mangement.UserManagement;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO  {


    //public void setDataSource(DataSource ds);

    /**
     * This is the method to be used to create
     * a record in the User table.
     */
    public void create(User user);

    public String updatePassword(int UserId, String oldPassword, String newPassword);

    public String deleteUser(int id);

    /**
     * This is the method to be used to list down
     * all the records from the User table.
     */
    public List<User> viewAllUsers();
}
