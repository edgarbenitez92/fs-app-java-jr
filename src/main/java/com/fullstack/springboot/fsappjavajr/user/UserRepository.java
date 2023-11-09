package com.fullstack.springboot.fsappjavajr.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private Environment environment;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserRepository(Environment environment) {
		this.environment = environment;
	}

	public User getUserByName(String user_name) {
		String query = environment.getProperty("query.FIND_USER_BY_NAME");
		Map<String, Object> userQueryResult = jdbcTemplate.queryForMap(query, user_name);

		User user = new User();
		user.setId((int) userQueryResult.get("id"));
		user.setUser((String) userQueryResult.get("user_name"));

		return user;
	}
}
