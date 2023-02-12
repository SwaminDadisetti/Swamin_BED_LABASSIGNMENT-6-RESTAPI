package com.greatlearning.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.registration.model.User;
import com.greatlearning.registration.repository.UserRepository;
import com.greatlearning.registration.security.MyUserDetails;

public class UserDetailsServiceImpl implements  UserDetailsService {

	@Autowired
    private UserRepository userRepository;
     
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		 
		        User user = userRepository.getUserByUsername(username);
		 
		        if (user == null) {
		            throw new UsernameNotFoundException("Could not find user");
		        }
		         
		        return new MyUserDetails(user);
	}

}
