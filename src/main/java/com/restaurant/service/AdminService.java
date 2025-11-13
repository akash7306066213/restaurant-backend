package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Role;
import com.restaurant.model.User;
import com.restaurant.repository.UserRepository;

@Service
public class AdminService {
	
	 @Autowired
	 private UserRepository userRepository;

	 public List<User> getAllCustomers() {
		    return userRepository.findByRole(Role.ROLE_CUSTOMER);
		}


}
