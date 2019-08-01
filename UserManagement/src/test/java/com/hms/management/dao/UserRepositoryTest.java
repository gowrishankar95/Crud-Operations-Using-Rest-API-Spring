package com.hms.management.dao;

import com.hms.management.user.User;
import com.hms.management.user.UserMapper;
import com.hms.management.enums.ExceptionCodeDescription;

import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;


@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    User user = new User("gowri","12789");

    @BeforeAll
    public static void beforeAll(){
        System.out.println("before all");
    }
    @org.junit.jupiter.api.Test
    public void viewAllUsersTest() {



        User newUser1 = new User("gowri","19");
        User newUser2 = new User("gowr","189");
        User newUser3 = new User("gow","1289");
        User newUser4 = new User("gw","1278");
        User newUser5 = new User("goi","19");

        HashMap<Integer,User> usersHashMap = new HashMap<>();
        List<User> list = new ArrayList<>();
        newUser1 = userRepository.create(newUser1);
        newUser2 = userRepository.create(newUser2);
        newUser3 = userRepository.create(newUser3);
        newUser4 = userRepository.create(newUser4);
        newUser5 = userRepository.create(newUser5);

        usersHashMap.put(newUser1.getUserId(), newUser1);
        usersHashMap.put(newUser2.getUserId(), newUser2);
        usersHashMap.put(newUser3.getUserId(), newUser3);
        usersHashMap.put(newUser4.getUserId(), newUser4);
        usersHashMap.put(newUser5.getUserId(), newUser5);

        List<User> usersListFromDatabase = userRepository.viewAllUsers();

        assertThat(usersHashMap).isNotEmpty();

        for(int i = 0; i < list.size(); i++){
            assertThat(usersHashMap).containsKey(list.get(i).getUserId());
        }
    }

    @Test
    public void userInsertionTest(){
        int rowsInserted = 1;
        long noOfRowsBeforeInsert = (long) countRowsInTable(jdbcTemplate,"users");
        User newUser = userRepository.create(user);
        long noOfRowsAfterInsert = jdbcTemplate.queryForLong("select count(*) from users");

        assertThat(noOfRowsBeforeInsert + rowsInserted).isEqualTo(noOfRowsAfterInsert);

        User retrievedUser = jdbcTemplate.queryForObject("select * from users where userId =" +
                newUser.getUserId(), new UserMapper());

        assertThat(newUser).isEqualToComparingFieldByField(retrievedUser);
    }

    @Test
    public  void deleteUserTest() {
        User newUser = userRepository.create(user);
        long noOfRowsBeforeDelete = jdbcTemplate.queryForLong("select count(*) from users");

        userRepository.deleteUser(newUser.getUserId());
        long noOfRowsAfterDelete = jdbcTemplate.queryForLong("select count(*) from users");
        assertThat(noOfRowsBeforeDelete - 1).isEqualTo(noOfRowsAfterDelete);
        try{
            User retrievedUser = jdbcTemplate.queryForObject("select * from users where userId =" +
                    newUser.getUserId(), new UserMapper());
        }
        catch (Exception e){
            assertThat(e instanceof EmptyResultDataAccessException).isTrue();
        }

    }

    @Test
    public  void getUserTest(){
        User newUser = userRepository.create(user);
        User retrievedUser = userRepository.getUser(newUser.getUserId());
        assertThat(newUser).isEqualToComparingFieldByField(retrievedUser);
    }

    @Test
    public  void updatePasswordTest(){
        User newUser = userRepository.create(user);
        String newPassword = "SAGGSDASG";
        userRepository.updatePassword(newUser.getUserId(),newUser.getPassword(),newPassword);
        User userAfterUpdate = jdbcTemplate.queryForObject("select * from users where userId =" +
                newUser.getUserId(), new UserMapper());
        assertThat(userAfterUpdate.getPassword()).isEqualTo(newPassword);
    }

    @Test
    public  void updatePasswordWithInvalidPasswordtest(){
        String invalidPassword = "hello";
        User newUser = userRepository.create(user);
        String newPassword = "SAGGSDASG";
        String response = userRepository.updatePassword(newUser.getUserId(),invalidPassword,newPassword);
        assertThat(response.equals(ExceptionCodeDescription.INVALID_PASSWORD.getDescription())).isTrue();
    }





}
