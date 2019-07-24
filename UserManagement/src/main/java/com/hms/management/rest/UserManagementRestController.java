package com.hms.management.rest;
import com.hms.management.User;
import com.hms.management.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserManagementRestController {

    @Autowired()
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/Users")
    public List<User> viewUsers() {
        return userRepository.viewAllUsers();

    }

    @PostMapping("/Users")
    public Object createUsers(@RequestParam(value = "userName", defaultValue = " ") String userName,
                              @RequestParam(value = "password", defaultValue = " ") String password) {

        User user = new User(userName, password);

        if (user.getUsername().trim().isEmpty()) {
            System.out.println("name is epmty");
            return "empty name";
        } else if (user.getUsername().trim().length() > 20) {
            System.out.println("name cannot exceed 20 characters");
            return "name cannot exceed 20 characters";
        } else if (user.getPassword().trim().isEmpty()) {
            System.out.println("password is epmty");
            return "password is epmty";
        } else if (user.getPassword().length() > 20) {
            System.out.println("password cannot exceed 20 characters");
            return "password cannot exceed 20 characters";
        } else {
            userRepository.create(user);
            return "created new user";
        }

    }

    @PutMapping("/Users")
    public Object updatePassword(@RequestParam(value = "id", defaultValue = " ") int id,
                                 @RequestParam(value = "opassword", defaultValue = " ") String oldPassword,
                                 @RequestParam(value = "npassword", defaultValue = " ") String newPassword) {

        return userRepository.updatePassword(id, oldPassword, newPassword);

    }

    @DeleteMapping("/Users")
    public String deleteUser(@RequestParam(value = "id", defaultValue = " ") int id) {

        return userRepository.deleteUser(id);
    }
}

