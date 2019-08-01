package com.hms.management.dao;

import com.hms.management.user.User;

import java.util.List;

public interface UserRepository {

    /**
     * This is the method to be used to create
     * user.
     */
    User create(User user);

    /**
     * This is the method to be used to update
     * password of a user. should provide userId,
     * old password and new password
     */
    String updatePassword(int UserId, String oldPassword, String newPassword);

    /**
     * This is the method to be used to delete
     * user. should provide userID
     */
    User deleteUser(int id);

    /**
     * This is the method to be used to view
     * all users.
     */
    List<User> viewAllUsers();

    /**
     * This is the method to be used to get
     * a user with perticular userId
     */
    User getUser(int userId);


}
