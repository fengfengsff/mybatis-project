package com.geeknote;

import com.geeknote.constant.SexEnum;
import com.geeknote.domain.User;
import com.geeknote.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Slf4j
public class MybatsTest {

	private SqlSessionFactory sqlSessionFactory ;

	@Before
	public void before() throws Exception{
		String configPath = "mybatis/config/mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(configPath);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	@Test
	public void test_insert() {
		Random random = new Random();
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			User user = new User("zhangsan" + random.nextInt(1000), random.nextInt(50), SexEnum.MAN);
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			boolean i = mapper.insert(user);
			Assert.assertTrue(i);
		}
	}

	@Test
	public void test_findAll() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			List<User> users = mapper.findAll();
			for (User user : users) {
				log.info("User = {}", user.toString());
			}
		}
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			List<User> users = mapper.findAll();
			for (User user : users) {
				log.info("User = {}", user.toString());
			}
		}
	}

	@Test
	public void test_findByName() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			MDC.put("traceId", "1111111111111");
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			User user = mapper.findByName("zhangsan");
			log.info("User = {}", user.toString());
		}
	}

	@Test
	public void test_findByParam() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			User user = new User(null, 18, null);
			List<User> users = mapper.findByParam(user);
			for (User u : users) {
				log.info("User = {}", u.toString());
			}
		}
	}
}
