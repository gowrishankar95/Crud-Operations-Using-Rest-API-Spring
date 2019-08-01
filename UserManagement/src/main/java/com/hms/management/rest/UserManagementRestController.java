package com.hms.management.rest;

import com.hms.management.exception.ApiException;
import com.hms.management.enums.ExceptionCodeDescription;
import com.hms.management.user.User;
import com.hms.management.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserManagementRestController {

    private static final int USERNAME_MAX_LENGHT = 20;

    private static final int USERPASSWORD_MAX_LENGHT = 20;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired()
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public List<User> getUserList() {
        logger.info("recieved a get request from client to get user list");
        return userRepository.viewAllUsers();

    }

    @GetMapping(value = "/user")
    public User getUser(@RequestParam(value = "userId", defaultValue = " ") int userId) {
        logger.info("recieved a get request from user to return a user with userID {}", userId);
        return userRepository.getUser(userId);

    }

    @PostMapping("/users")
    public User createUser(@RequestParam(value = "userName", defaultValue = " ") String userName,
                                     @RequestParam(value = "password", defaultValue = " ")
                                             String password) throws ApiException {

        logger.info("recieved a  request create user ");
        User user = new User(userName, password);

        if (user.getUsername().trim().isEmpty() || user.getUsername().trim().length() > USERNAME_MAX_LENGHT) {
            logger.info(ExceptionCodeDescription.INVALID_USERNAME.getDescription());
            throw new ApiException("invalid user name");
        } else if (user.getPassword().trim().isEmpty() || user.getPassword().length() > USERPASSWORD_MAX_LENGHT) {
            logger.info("invalid password and going to throw an exception");
            throw new ApiException(ExceptionCodeDescription.INVALID_PASSWORD.getDescription());
        } else {
            return userRepository.create(user);

        }

    }

    @PutMapping("/users")
    public String updatePassword(@RequestParam(value = "userId", defaultValue = " ") int id,
                                 @RequestParam(value = "oldPassword", defaultValue = " ") String oldPassword,
                                 @RequestParam(value = "newPassword", defaultValue = " ") String newPassword) {
        logger.info("request to change password of userId {} " , id);

        return userRepository.updatePassword(id, oldPassword, newPassword);


    }

    @DeleteMapping("/users")
    public User deleteUser(@RequestParam(value = "userId", defaultValue = " ") int id) {
        logger.info("request to change password from userId {}", id);
        return userRepository.deleteUser(id);

    }
}

