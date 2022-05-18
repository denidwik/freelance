package com.project.freelance.security;

import com.project.freelance.exception.NotFoundException;
import com.project.freelance.model.UserEntity;
import com.project.freelance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailOrUserName) {
        UserEntity user = userRepository.findByEmailOrUserName(emailOrUserName, emailOrUserName)
                .orElseThrow(() ->
                        new NotFoundException("User not found [email or username : " + emailOrUserName + "]")
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found [id: " + id + "]")
        );

        return UserPrincipal.create(user);
    }
}
