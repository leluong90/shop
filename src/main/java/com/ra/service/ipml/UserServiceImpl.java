package com.ra.service.ipml;

import com.ra.exception.UserException;
import com.ra.model.dto.request.UserRequestDTO;
import com.ra.model.dto.response.UserResponseDTO;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.security.jwt.JwtProvider;
import com.ra.security.user_principal.UserPrincipal;
import com.ra.service.RoleService;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider ;
    @Autowired
    private RoleService roleService;
    @Override
    public User register(User user) {
//        ma hoa mat khau
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        roles
        Set<Role> roles = new HashSet<>();
//        register cua user thi coi no la USER
        if(user.getRoles() == null || user.getRoles().isEmpty()){
            roles.add(roleService.findByRoleName("USER"));
        }else {
//        Tao tk va phan quyen thi phai co quyen ADMIN
            user.getRoles().forEach(role -> {
                roles.add(roleService.findByRoleName(role.getName()));
            });


        }
        User newUser = new User() ;
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setStatus(user.getStatus());
        newUser.setRoles(roles);
//        userRepository.save(newUser);
        return userRepository.save(newUser);
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        try {
            Authentication authentication ;
            authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getEmail(),userRequestDTO.getPassword()));
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            return UserResponseDTO.builder()
                    .token(jwtProvider.generateToken(userPrincipal))
                    .email(userPrincipal.getEmail())
                    .userName(userPrincipal.getUsername())
                    .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                    .build();

        }catch (AuthenticationException authenticationException){
            System.err.println(authenticationException);
            return null;
        }

    }

    @Override
    public User findById(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }throw new UserException("user not found with id :" + id);

//        return null;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException{
        String email = jwtProvider.getUserNameFromToken(jwt);
        User user =userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("user not found with email" + email);
        }
        return user;
    }


}
