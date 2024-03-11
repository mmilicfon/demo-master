package com.mm.app.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.app.controller.UserController;
import com.mm.app.exception.EntityExistsException;
import com.mm.app.model.User;
import com.mm.app.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTests {

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void saveUserSuccessTest() throws Exception {
		User user = new User(1l, "UC1", "UC1", "uc1@mail.com", "uc1");
		when(userService.save(user)).thenReturn(user);
		mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", equalTo((int) user.getId())))
				.andExpect(jsonPath("$.firstName", equalTo(user.getFirstName())))
				.andExpect(jsonPath("$.lastName", equalTo(user.getLastName())))
				.andExpect(jsonPath("$.email", equalTo(user.getEmail())))
				.andExpect(jsonPath("$.password", equalTo(user.getPassword())));
		verify(userService, times(1)).save(user);

	}

	@Test
	public void saveUserFailureTest() throws Exception {
		User user = new User(2l, "UC2", "UC2", "uc2@mail.com", "uc2");
		when(userService.save(user)).thenThrow(EntityExistsException.class);
		mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isBadRequest());
	}

	@Test
	public void findAllTest() throws Exception {
		User user1 = new User(3l, "UC3", "UC3", "uc3@mail.com", "uc3");
		User user2 = new User(4l, "UC4", "UC4", "uc4@mail.com", "uc4");
		List<User> users = Arrays.asList(user1, user2);
		when(userService.findAll()).thenReturn(users);
		MvcResult result = mockMvc.perform(get("/user/all")).andExpect(status().isOk()).andReturn();
		List<User> returnedUsers = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<List<User>>() {
				});
		assertTrue(returnedUsers.contains(user1));
		assertTrue(returnedUsers.contains(user2));
	}

}
