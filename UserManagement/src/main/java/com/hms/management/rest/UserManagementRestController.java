package com.hms.management.rest;
import com.hms.management.User;
import com.hms.management.dao.UserRepository;
import com.hms.management.rest.Exceptions.InvalidNameException;
import com.hms.management.rest.Exceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserManagementRestController {

    @Autowired()
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public List<User> getUserList() {
        return userRepository.viewAllUsers();

    }

    @PostMapping("/users")
    public Object createUser(@RequestParam(value = "userName", defaultValue = " ") String userName,
                                     @RequestParam(value = "password", defaultValue = " ") String password) {

        User user = new User(userName, password);

        if (user.getUsername().trim().isEmpty() || user.getUsername().trim().length() > 20) {
            throw new InvalidNameException();
        } else if (user.getPassword().trim().isEmpty() || user.getPassword().length() > 20) {
            throw new InvalidPasswordException();
        } else {
            return userRepository.create(user);
        }

    }

    @PutMapping("/users")
    public Object updatePassword(@RequestParam(value = "id", defaultValue = " ") int id,
                                 @RequestParam(value = "opassword", defaultValue = " ") String oldPassword,
                                 @RequestParam(value = "npassword", defaultValue = " ") String newPassword) {

        return userRepository.updatePassword(id, oldPassword, newPassword);

    }

    @DeleteMapping("/users")
    public String deleteUser(@RequestParam(value = "id", defaultValue = " ") int id) {

        return userRepository.deleteUser(id);
    }
}

