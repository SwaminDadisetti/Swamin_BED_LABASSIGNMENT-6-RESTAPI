package com.greatlearning.registration.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.greatlearning.registration.model.Roles;
import com.greatlearning.registration.model.User;

public class MyUserDetails  implements org.springframework.security.core.userdetails.UserDetails {

	 private User user;
	    
	    public MyUserDetails(User user) {
	        this.user = user;
	    }
	    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    	 List<Roles> roles = user.getRoles();
	         List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	          
	         for (Roles role : roles) {
	             authorities.add(new SimpleGrantedAuthority(role.getName()));
	         }
	          
	         return authorities;
	}

	@Override
	public String getPassword() {
	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}
	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
