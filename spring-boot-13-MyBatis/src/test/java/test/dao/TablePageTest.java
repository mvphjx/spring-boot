package test.dao;

import com.han.AppMybatis;
import com.han.dao.UserInfoMapper;
import com.han.entity.UserInfo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMybatis.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class TablePageTest
{

	@Autowired
	UserInfoMapper userDao;

	/**
	 * 分页方法
	 * @throws Exception
	 */
	@Test
	public void pageUserInfo() throws Exception {
		PageHelper.startPage(2, 30);
		List<UserInfo> users=userDao.findAll();
		System.out.println(">>>>>>查询结果数量："+users.size());
		PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(users);
		String json = JSON.toJSONString(pageInfo);
		System.out.println(">>>>>>查询结果：");
		System.out.println(json);

	}
	@Test
	public void pageInfo() throws Exception {
		PageHelper.startPage(2, 30);
		List<Map<String, Object>> maps = userDao.commonSearch();
		System.out.println(">>>>>>查询结果数量："+maps.size());
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maps);
		String json = JSON.toJSONString(pageInfo);
		System.out.println(">>>>>>查询结果：");
		System.out.println(json);

	}
}
