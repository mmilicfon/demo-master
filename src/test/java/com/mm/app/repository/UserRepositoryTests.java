package com.mm.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mm.app.model.User;

@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void saveTest() {
		User user = userRepository.save(new User(1l, "UR_1", "UR_1", "ur1@mail.com", "ur1"));
		assertNotNull(user);
	}

	@Test
	public void deleteTest() {
		User user = userRepository.save(new User(2l, "UR_2", "UR_2", "ur2@mail.com", "ur2"));
		assertNotNull(user);
		userRepository.delete(user);
		Optional<User> deleted = userRepository.findById(user.getId());
		assertFalse(deleted.isPresent());
	}

	@Test
	public void findByEmailTest() {
		User user = userRepository.save(new User(3l, "UR_3", "UR_3", "ur3@mail.com", "ur3"));
		assertNotNull(user);
		Optional<User> u = userRepository.findByEmail(user.getEmail());
		assertTrue(u.isPresent());
		assertEquals(user, u.get());
	}

}
