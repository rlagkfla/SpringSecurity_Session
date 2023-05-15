package com.example.SpringSecurity.auth;


//import com.example.SpringSecurity.model.User;
import com.example.SpringSecurity.model.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class MemberDetails implements UserDetails {

    private final Users user;

    public MemberDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /* 첫번째 방법
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;*/

        /* 두번째 방법
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(()->{ return user.getRole();});
        return collect;*/

        /* 세번째 방법
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new SimpleGrantedAuthority(user.getRole()));
        return collect;*/

        return Collections.singleton(
            new SimpleGrantedAuthority(user.getRole())
        );


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
