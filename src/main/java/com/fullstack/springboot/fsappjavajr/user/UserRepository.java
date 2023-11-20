package com.fullstack.springboot.fsappjavajr.user;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public List<User> getUsers() {
		String query = environment.getProperty("query.FIND_USERS");
		List<Map<String, Object>> usersQueryResult = jdbcTemplate.queryForList(query);

		List<User> users = usersQueryResult.stream().map(row -> {
			User user = new User();
			user.setId((int) row.get("id"));
			user.setUser((String) row.get("user_name"));
			user.setPassword((String) row.get("password"));
			return user;
		}).collect(Collectors.toList());
		;

		return users;
	}

	public User getUserByName(String user_name) {
		String query = environment.getProperty("query.FIND_USER_BY_NAME");
		Map<String, Object> userQueryResult = jdbcTemplate.queryForMap(query, user_name);

		User user = new User();
		user.setId((int) userQueryResult.get("id"));
		user.setUser((String) userQueryResult.get("user_name"));
		user.setPassword((String) userQueryResult.get("password"));

		return user;
	}
}
