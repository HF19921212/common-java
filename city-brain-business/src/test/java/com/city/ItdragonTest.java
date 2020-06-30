package com.city;

import com.city.model.SysUsers;
import com.city.service.UserService;
import com.city.utils.ItdragonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @RunWith	它是一个运行器
 * @RunWith(SpringRunner.class) 表示让测试运行于Spring测试环境，不用启动spring容器即可使用Spring环境
 * @SpringBootTest(classes=StartApplication.class)  表示将StartApplication.class纳入到测试环境中，若不加这个则提示bean找不到。
 *
 * @author he.fan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=CityBrainBusinessApplication.class)
public class ItdragonTest {

    @Autowired
    private UserService userService;

    /**
     * 注册系统账户
     */
    @Test
    public void registerUser() {
        SysUsers user = new SysUsers();
        user.setId(Short.parseShort("1"));
        user.setName("admin");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        user.setCreatorId(Short.parseShort("0"));
        user.setRemark("系统管理员账户");
        //密码加盐处理
        ItdragonUtils.entryptPassword(user);
        userService.registerUser(user);
    }

    /**
     * 账户密码解密
     */
    @Test
    public void decryptPassword() {
        SysUsers user = new SysUsers();
        user.setId(Short.parseShort("1"));
        user.setName("admin");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        user.setCreatorId(Short.parseShort("0"));
        ItdragonUtils.decryptPassword(user,"f9c9a1c61122c8b0e98eebd3c014a42a");
        System.out.println(user);
    }

}
