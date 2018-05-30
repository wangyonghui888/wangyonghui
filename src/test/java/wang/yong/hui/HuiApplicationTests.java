package wang.yong.hui;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import wang.yong.hui.config.JpaConfiguration;
import wang.yong.hui.domain.Deparment;
import wang.yong.hui.domain.Role;
import wang.yong.hui.domain.User;
import wang.yong.hui.respository.DeparmentRepository;
import wang.yong.hui.respository.RoleRepository;
import wang.yong.hui.respository.UserRepository;

import java.util.Date;
import java.util.List;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =  {JpaConfiguration.class})
@EnableAutoConfiguration(exclude = JpaRepositoriesAutoConfiguration.class)
@Slf4j
public class HuiApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(HuiApplicationTests.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	DeparmentRepository deparmentRepository;

	@Autowired
	RoleRepository roleRepository;

	@Before
	public void initData() {
		userRepository.deleteAll();
		deparmentRepository.deleteAll();
		roleRepository.deleteAll();

		Deparment deparment = new Deparment();
		deparment.setName("开发部");
		deparmentRepository.save(deparment);
		org.springframework.util.Assert.notNull(deparment.getId());

		Role role = new Role();
		role.setName("admin");
		roleRepository.save(role);
		org.springframework.util.Assert.notNull(role.getId());

		User user = new User();
		user.setName("user");
		user.setCreatedate(new Date());
		user.setDeparment(deparment);
		List<Role> roles = roleRepository.findAll();
		org.springframework.util.Assert.notNull(roles);
		user.setRoles(roles);

		userRepository.save(user);
		org.springframework.util.Assert.notNull(user.getId());
	}
	@Test
	public void findPage() {
		Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
	    Page<User> page = userRepository.findAll(pageable);
		org.springframework.util.Assert.notNull(page);

		for(User user :page.getContent()) {
			logger.info("==user ==user name:{}, deparment name:{}, role name:{} " + user.getName() + "==="
					+ user.getDeparment().getName() + user.getRoles().get(0).getName());
		}
	}

}
