package com.mm.app.service;

import java.util.List;

import com.mm.app.exception.EntityExistsException;
import com.mm.app.exception.InvalidEntityException;
import com.mm.app.model.User;

public interface UserService {

	User save(User user) throws EntityExistsException;

	List<User> findAll();

	void deleteById(Long id) throws InvalidEntityException;
}
