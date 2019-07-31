package com.hms.management;

import com.hms.management.dao.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    User user = new User("gowri","12789");

    @Test
    public  synchronized void viewAllUsersTest(){

        jdbcTemplate.execute("delete from users");

        User user1 = new User("gowri","19");
        User user2 = new User("gowr","189");
        User user3 = new User("gow","1289");
        User user4 = new User("gw","1278");
        User user5 = new User("goi","19");

        HashMap<Integer,User> usersHashMap = new HashMap<>();
        List<User> list = new ArrayList<>();
        user1 = userRepository.create(user1);
        user2 = userRepository.create(user2);
        user3 = userRepository.create(user3);
        user4 = userRepository.create(user4);
        user5 = userRepository.create(user5);

        usersHashMap.put(user1.getUserId(), user1);
        usersHashMap.put(user2.getUserId(), user2);
        usersHashMap.put(user3.getUserId(), user3);
        usersHashMap.put(user4.getUserId(), user4);
        usersHashMap.put(user5.getUserId(), user5);

        List<User> usersListFromDatabase = userRepository.viewAllUsers();

        for(int i = 0; i < list.size(); i++){
            Assert.assertTrue(usersListFromDatabase.size() == list.size() );
            Assert.assertTrue(usersHashMap.containsKey(list.get(i).getUserId()));
        }

    }

    @Test
    public synchronized void checkUserInsertion(){
        int rowsInserted = 1;
        long beforeInsert = (long) countRowsInTable(jdbcTemplate,"users");
        User user1 = userRepository.create(user);
        long afterInsert = jdbcTemplate.queryForLong("select count(*) from users");
        Assert.assertEquals(beforeInsert + rowsInserted, afterInsert );
        User user2 = jdbcTemplate.queryForObject("select * from users where userId =" +
                user1.getUserId(), new UserMapper());
        Assert.assertTrue(user.getUsername().equals(user2.getUsername()));
        Assert.assertTrue(user.getPassword().equals(user2.getPassword()));


    }
    @Test
    public synchronized void checkDelete(){
        User user1 = userRepository.create(user);
        long beforeDelete = jdbcTemplate.queryForLong("select count(*) from users");
        userRepository.deleteUser(user1.getUserId());
        long afterDelete = jdbcTemplate.queryForLong("select count(*) from users");
        Assert.assertEquals(beforeDelete - 1, afterDelete);
    }

    @Test
    public synchronized void checkGetUser(){
        User user1 = userRepository.create(user);
        User user2 = userRepository.getUser(user1.getUserId());
        Assert.assertTrue(user1.getUsername().equals(user2.getUsername()));
        Assert.assertTrue(user1.getPassword().equals(user2.getPassword()));
    }

    @Test
    public synchronized void checkUpdatePassword(){
        User user1 = userRepository.create(user);
        String newPassword = "SAGGSDASG";
        userRepository.updatePassword(user1.getUserId(),user1.getPassword(),newPassword);
        User user3 = jdbcTemplate.queryForObject("select * from users where userId =" +
                user1.getUserId(), new UserMapper());
        Assert.assertTrue(user3.getPassword().equals(newPassword));

    }
    @Test
    public synchronized void checkUpdatePasswordWithInvalidPassword(){
        String invalidPassword = "hello";
        User user1 = userRepository.create(user);
        String newPassword = "SAGGSDASG";
        String response = userRepository.updatePassword(user1.getUserId(),invalidPassword,newPassword);
        System.out.println(response);
        Assert.assertTrue(response.equals(ExceptionCodeDescription.INVALID_PASSWORD.getDescription()));

    }





}
