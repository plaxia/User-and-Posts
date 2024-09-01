package com.rest.webservices.resftul_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	private static int usersCount = 0;
	static {
		users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Andrei", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Iva", LocalDate.now().minusYears(20)));
	}

	public List<User> findAllUsers() {

		return users;
	}

	public User findOne(int id) {

		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	public void deleteById(int id) {

		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}

}
