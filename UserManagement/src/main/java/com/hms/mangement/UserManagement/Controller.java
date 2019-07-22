package com.hms.mangement.UserManagement;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private ApplicationContext context ;
    private UserJDBCTemplate userJDBCTemplate ;
    

    public Controller() {
        this.context = new ClassPathXmlApplicationContext("been.xml");
        this.userJDBCTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
    }

    @GetMapping(value = "/Users")
    public String viewUsers() {
        System.out.println("hello get");

        List<User> users = userJDBCTemplate.viewAllUsers();
        if (users != null) {
            return new Gson().toJson(users);
        }
        return "error";
    }

    @PostMapping("/Users")
    public String createUsers(@RequestParam(value = "userName", defaultValue = " ") String userName,
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
        }
        else {
            userJDBCTemplate.create(user);
            return "created new user";
        }

    }

    @PutMapping("/Users")
    public String updatePassword(@RequestParam(value = "id", defaultValue = " ") int id,
                                 @RequestParam(value = "opassword", defaultValue = " ") String oldPassword,
                                 @RequestParam(value = "npassword", defaultValue = " ") String newPassword) {

        return  userJDBCTemplate.updatePassword(id,oldPassword,newPassword);

    }

    @DeleteMapping("/Users")
    public String deleteUser(@RequestParam(value = "id", defaultValue = " ") int id) {

        return userJDBCTemplate.deleteUser(id);
    }

}
