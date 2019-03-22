package com.springboot.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootIntegrationShiroApplicationTests {

	@Test
	public void testMd5() {
		String password=new Md5Hash("123456","user01",5).toHex();  //明文密码  盐值 加密次数

		System.out.println(password);//c97ff7cc3fadf3c85e2bf02d80a8f59f 数据库中存储值
	}

}
