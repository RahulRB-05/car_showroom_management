package com.example.AutoHub.security;

import com.example.AutoHub.exception.NotFoundException;
import com.example.AutoHub.user.entity.User;
import com.example.AutoHub.user.enums.Status;
import com.example.AutoHub.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email).orElseThrow(
                ()->new NotFoundException("User Not found...")
        );

        if(user.getStatus() == Status.INACTIVE){
            throw new UsernameNotFoundException("User inactive");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getEmail(),
                List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole().name()))
        );
    }
}
