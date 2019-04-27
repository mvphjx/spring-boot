package test.dao;

import com.han.AppMybatis;
import com.han.bzo.UserObject;
import com.han.entity.UserInfo;
import com.han.entity.UserPassword;
import com.han.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMybatis.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//@Transactional
public class UserSerciveTest
{

    @Autowired
    UserService userService;

    @Test
    public void addTest()
    {
        UserObject userObject = new UserObject();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("N" + new Date().getTime());
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword("P" + new Date().getTime());
        userObject.userInfo = userInfo;
        userObject.userPassword = userPassword;
        int add = userService.add(userObject);
        System.out.println(">>>>>>>success:" + add);
        System.out.println(">>>>>>>json:" + JSON.toJSONString(userObject));
    }

    @Test
    public void addMany()
    {
        for (int i = 0; i < 100; i++)
        {
            this.addTest();
        }
    }

}
