package com.mm.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mm.app.exception.EntityExistsException;
import com.mm.app.exception.InvalidEntityException;
import com.mm.app.model.User;
import com.mm.app.repository.UserRepository;
import com.mm.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) throws EntityExistsException {
		Optional<User> u = userRepository.findByEmail(user.getEmail());
		if (u.isPresent()) {
			throw new EntityExistsException("Username already exists!");
		}
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteById(Long id) throws InvalidEntityException {
		Optional<User> u = userRepository.findById(id);
		if (!u.isPresent()) {
			throw new InvalidEntityException("Invalid user!");
		}
		userRepository.deleteById(id);
	}

}
