package com.hms.management.dao;

import com.hms.management.User;

import java.util.List;

public interface UserRepository {
    //todo : Add java doc

    User create(User user);

    String updatePassword(int UserId, String oldPassword, String newPassword);

    User deleteUser(int id);

    List<User> viewAllUsers();

    User getUser(int userId);


}
