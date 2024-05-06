package com.ra.security.user_principal;

import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository ;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username);
////        if (user.isPresent()){
////            User user = userOptional.get();
////            UserPrincipal userPrincipal = UserPrincipal.builder().user(user).authorities(user.getRoles().stream().map(item -> new SimpleGrantedAuthority(item.getName())).toList()).build();
////            return userPrincipal ;
////        }
//        return UserPrincipal.build(user);
//    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
//        if (user.isPresent()){
//            User user = userOptional.get();
//            UserPrincipal userPrincipal = UserPrincipal.builder().user(user).authorities(user.getRoles().stream().map(item -> new SimpleGrantedAuthority(item.getName())).toList()).build();
//            return userPrincipal ;
//        }
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return UserPrincipal.build(user);
    }
}

