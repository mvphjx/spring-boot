package test.dao;

import com.han.AppMybatis;
import com.han.dao.UserInfoMapper;
import com.han.entity.UserInfo;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMybatis.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//@Transactional
public class UserInfoMapperTest {

	@Autowired
	UserInfoMapper userDao;

	@Test
	public void findAll() throws Exception {
		List<UserInfo> lui = userDao.findAll();
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + JSON.toJSONString(lui));
		System.out.println("\n\n\n\n\n");
	}

	@Test
	public void queryById() throws Exception {
		List<UserInfo> lui = userDao.queryById();
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + JSON.toJSONString(lui));
		System.out.println("\n\n\n\n\n");
	}

	@Test
	public void findByName() throws Exception {
		List<UserInfo> lui = userDao.findByName("test2");
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + lui);
		System.out.println("\n\n\n\n\n");
	}

	@Test
	//@Rollback
	public void insert() throws Exception {
		UserInfo ui = new UserInfo();
		ui.setUsername("余姣姣");
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + userDao.insert(ui)+"***"+ui.getId());
		System.out.println("\n\n\n\n\n");
	}

	@Test
	public void insertByMap() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 13);
		map.put("username", "姣姣");
		map.put("password", "password");
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + userDao.insertByMap(map));
		System.out.println("\n\n\n\n\n");
	}
	@Test
	public void delete() throws Exception {
		userDao.delete(1);
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + JSON.toJSONString(userDao.findById(1)));
		System.out.println("\n\n\n\n\n");
	}


}
