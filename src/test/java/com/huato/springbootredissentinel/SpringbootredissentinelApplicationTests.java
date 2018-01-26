package com.huato.springbootredissentinel;

import com.huato.springbootredissentinel.entity.User;
import com.huato.springbootredissentinel.service.RedisSentinelServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootredissentinelApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Autowired
	private RedisSentinelServer redisSentinelConfig;

	@Test
	public void sentinelSet(){
		User user = new User();
		user.setId("007");
		user.setAge("31");
		user.setName("张三");

		redisSentinelConfig.sentinelSet(user);
	}

	@Test
	public void sentinelGet(){
		String str = redisSentinelConfig.sentinelGet("007");
		System.out.println(str);
	}

}
