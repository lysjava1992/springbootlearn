package com.springboot.redis;

import com.springboot.redis.dao.UserDao;
import com.springboot.redis.entity.User;
import net.sf.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootIntegrationRedisApplicationTests {

	@Autowired
	UserDao userDao;

	@Autowired
	StringRedisTemplate stringRedisTemplate;//key-value都是字符串
	@Autowired
	RedisTemplate redisTemplate; //key-value都是对象

	@Autowired
	RedisTemplate <Object,Object> myredisTemplate;

	/**
	 * Redis常见的五大数据类型
	 *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
	 *  stringRedisTemplate.opsForValue()[String（字符串）]
	 *  stringRedisTemplate.opsForList()[List（列表）]
	 *  stringRedisTemplate.opsForSet()[Set（集合）]
	 *  stringRedisTemplate.opsForHash()[Hash（散列）]
	 *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
	 */

	/**
	 * String
	 */
    @Test
	@Ignore
	public void test01(){
    	//存
    	stringRedisTemplate.opsForValue().append("key01","value-String");
    	//取
         String msg=stringRedisTemplate.opsForValue().get("key01");
         System.out.println(msg);

	}

	/**
	 * 集合操作
	 */
	@Test
	@Ignore
	public void test02(){
    	//存
    	stringRedisTemplate.opsForList().leftPush("keyList","1");
		stringRedisTemplate.opsForList().leftPush("keyList","2");
		//取
//		String value=stringRedisTemplate.opsForList().leftPop("keyList");
//		System.out.println(value);
	}

	@Test
	@Ignore
	public void  test03(){
    	User user=userDao.findByUserId(1);
    	redisTemplate.opsForValue().set("user-01",user);
	}

	/**
	 * 默认序列化器下 使用json存储对象
	 */
	@Test
    @Ignore
	public void  test04(){
		User user=userDao.findByUserId(1);
        System.out.println(user);
        JSONObject.fromObject(user);
        redisTemplate.opsForValue().set("json-01",JSONObject.fromObject(user).toString());
	}

	/**
	 * 根据自定义的序列化模板存储对象
	 */
	@Test
	@Ignore
	public void test05() {
		User user=userDao.findByUserId(1);
		myredisTemplate.opsForValue().set("user-001",user);
	}

	/**
	 * 获取通过key获取对象类型值
	 */
	@Test
	@Ignore
    public void test06(){
	User user= (User) myredisTemplate.opsForValue().get("user-001");
      System.out.println(user);
     }

	/**
	 * 删除操作
	 */
	@Test
	@Ignore
    public void test(){
		myredisTemplate.delete("user-001");
    }
	/**
	 * 设置时间
	 */
	@Test
     public  void test07(){
		 stringRedisTemplate.opsForValue().set("time","10s",100, TimeUnit.SECONDS);
	 }
}
