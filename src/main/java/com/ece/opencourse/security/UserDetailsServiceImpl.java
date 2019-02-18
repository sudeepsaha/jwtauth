package com.ece.opencourse.security;

import com.ece.opencourse.entity.UserRole;
import com.ece.opencourse.repository.UserRoleRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRole userRole = userRoleRepository.findByUserName(username);
        if (userRole != null) {

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
            return new User(userRole.getUserName(), userRole.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException(username);
        }

    }

}
