package com.gmail.zhangtiejun.spring.embedded.hsql.smoketesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gmail.zhangtiejun.spring.embedded.hsql.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-context.xml"})
public class SmokeTest {
	@Resource
	private DataSource dataSource;
	
	private JdbcTemplate template;

	@Before
	public void setUp() throws Exception {
		template = new JdbcTemplate(dataSource);
	}

	@Test
	public void smoke() {
		List<User> list = template.query("SELECT * FROM USERS", ParameterizedBeanPropertyRowMapper.newInstance(User.class));
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertEquals(60, list.size());
	}
}
