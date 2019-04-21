package cn.han.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import cn.han.App;
import cn.han.model.one2one.Address;
import cn.han.model.one2one.User;
import cn.han.repository.one2one.AddressRepository;
import cn.han.repository.one2one.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class)
@ActiveProfiles("dev")
public class One2oneTest {

	@Resource
	AddressRepository addressrepo;

	@Resource
	UserRepository userrepo;

	/**
	 * 单独保存user
	 * success()
	 */
	@Test
	public void test11() throws Exception {
		User u = new User();
		u.setUserName("root");
		User save = userrepo.save(u);
		System.out.println(save);
	}
	/**
	 * 单独保存user
	 * success()
	 */
	@Test
	public void test12() throws Exception {
		User u = new User("root");
		User save = userrepo.save(u);
		System.out.println(save);
	}

	/**
	 * 单独保存address
	 * failure: attempted to assign id from null one-to-one property [cn.han.model.one2one.Address.user]
	 */
	@Test
	public void test2() {
		Address address = new Address("一二一大街", "昆明市");
		addressrepo.save(address);
	}


}
