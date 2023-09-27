package com.sejong.creativesemester.login.domain;

import com.sejong.creativesemester.user.entity.Role;
import com.sejong.creativesemester.user.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@NoArgsConstructor
@Builder
public class AuthUser implements UserDetails {

    private User user;

    public AuthUser(User user) {
        this.user = user;
    }

    public Role getRole(){
        return user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    public String getStudentNum(){
        return user.getStudentNum();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
