package com.hms.management.dao;

import com.hms.management.User;

import java.util.List;

public interface UserRepository {

    public void create(User user);

    public String updatePassword(int UserId, String oldPassword, String newPassword);

    public String deleteUser(int id);

    public List<User> viewAllUsers();
}
