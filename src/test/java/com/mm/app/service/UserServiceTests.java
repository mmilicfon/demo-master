package com.mm.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mm.app.exception.EntityExistsException;
import com.mm.app.exception.InvalidEntityException;
import com.mm.app.model.User;
import com.mm.app.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void saveSuccessTest() throws EntityExistsException {
		User user = new User(1l, "US_1", "US_1", "us1@mail.com", "us1");
		// when .. then
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
		User u = userService.save(user);
		assertNotNull(u);
		assertEquals(user, u);
	}

	@Test
	public void saveFailureTest() {
		User user = new User(2l, "US_2", "US_2", "us2@mail.com", "us2");
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		assertThrows(EntityExistsException.class, () -> {
			userService.save(user);
		});
	}

	@Test
	public void deleteSuccessTest() throws InvalidEntityException {
		User user = new User(3l, "US_3", "US_3", "us3@mail.com", "us3");
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		userService.deleteById(user.getId());
		verify(userRepository, times(1)).deleteById(user.getId());
	}

	@Test
	public void deleteFailureTest() {
		User user = new User(4l, "US_4", "US_4", "us4@mail.com", "us4");
		when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
		assertThrows(InvalidEntityException.class, () -> {
			userService.deleteById(user.getId());
		});
	}

}
