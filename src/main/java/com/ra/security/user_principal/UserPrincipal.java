package com.ra.security.user_principal;

import com.ra.model.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Builder
public class UserPrincipal implements UserDetails {
    private Long id ;
    private String email ;
    private String userName ;
    private String password ;
    private User user ;
    private Collection<?extends GrantedAuthority> authorities ;


    public static UserDetails build(User user){
        return UserPrincipal.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(item-> new SimpleGrantedAuthority(item.getName())).toList()).build();
    }
    @Override
    public  Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
//        Boolean newStatus = ;
//        if (newStatus ==null){
//            return true ;
//        }
//        return this.user.getStatus();
        return true;
    }
}
