package com.nakahama.simpenbackend.Auth.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.User.model.UserModel;
import com.nakahama.simpenbackend.User.repository.UserDb;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDb userDb;

    @Override
    public UserDetails loadUserByUsername(String key) throws UsernameNotFoundException {
        // use email instead of username
        UserModel user = userDb.findByEmail(key);
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        grantedAuthority.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
        return new User(user.getEmail(), user.getPassword(), grantedAuthority);
    }
}
