package wang.yong.hui;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wang.yong.hui.config.RedisConfig;
import wang.yong.hui.domain.Deparment;
import wang.yong.hui.domain.Role;
import wang.yong.hui.domain.User;
import wang.yong.hui.respository.UserRedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = {RedisConfig.class,UserRedis.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedisTest {

    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

   @Autowired
   private UserRedis userRedis;
    @Before
    public void setup() {

        Deparment deparment = new Deparment();
        deparment.setName("开发部");

        Role role = new Role();
        role.setName("admin");

        User user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDeparment(deparment);

        List<Role> roles = new ArrayList<Role>();
        roles.add(role);

        user.setRoles(roles);

        userRedis.delete(this.getClass().getName() + ":userByname:" + user.getName());
        userRedis.add(this.getClass().getName() + ":userByname:" + user.getName(), 10L, user);
    }
    @Test
    public void get() {
        User user = userRedis.get(this.getClass().getName() + ":userByname:user");
        org.springframework.util.Assert.notNull(user, "对象不为空");
        logger.info("==userInfo== name:{},deparment{},role:{}",
                user.getName(),user.getDeparment().getName(),user.getRoles().get(0).getName());
    }
}
