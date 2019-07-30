package com.hms.management;


import com.hms.management.dao.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;



import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public synchronized void checkValidUserInsertion(){
        int rowsInserted = 1;
        User user = new User("gowri","12789");
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

        User user = new User("gowri","12789");
        User user1 = userRepository.create(user);
        long beforeDelete = jdbcTemplate.queryForLong("select count(*) from users");
        userRepository.deleteUser(user1.getUserId());
        long afterDelete = jdbcTemplate.queryForLong("select count(*) from users");
        Assert.assertEquals(beforeDelete - 1, afterDelete);
    }

    @Test
    public synchronized void checkGetUser(){
        User user = new User("gowri","12789");
        User user1 = userRepository.create(user);
        Assert.assertTrue(user1.getUsername().equals(user.getUsername()));
        Assert.assertTrue(user1.getPassword().equals(user.getPassword()));
    }

    @Test
    public synchronized void checkUpdatePassword(){
        User user = new User("gowri","12789");
        User user1 = userRepository.create(user);
        String newPassword = "SAGGSDASG";
        String response = userRepository.updatePassword(user1.getUserId(),user1.getPassword(),newPassword);
        User user3 = jdbcTemplate.queryForObject("select * from users where userId =" +
                user1.getUserId(), new UserMapper());
        Assert.assertTrue(user3.getPassword().equals(newPassword));

    }
    @Test
    public synchronized void checkUpdatePasswordWithInvalidPassword(){
        String invalidPassword = "hello";
        User user = new User("gowri","12789");
        User user1 = userRepository.create(user);
        String newPassword = "SAGGSDASG";
        String response = userRepository.updatePassword(user1.getUserId(),invalidPassword,newPassword);
        System.out.println(response);
        Assert.assertTrue(response.equals(ExceptionCodeDescription.INVALID_PASSWORD.getDescription()));

    }





}
