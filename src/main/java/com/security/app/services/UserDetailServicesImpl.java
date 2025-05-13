package com.security.app.services;

import com.security.app.controller.dto.AuthCreateUser;
import com.security.app.controller.dto.AuthLoginRequest;
import com.security.app.controller.dto.AuthResponse;
import com.security.app.entities.UserEntity;
import com.security.app.repository.UserRepository;
import com.security.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServicesImpl implements UserDetailsService {

    //vars
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Methods
    @Override
    public UserDetails loadUserByUsername(String username) {

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("el usuario " + username + " no existe..."));

        //* Se debe de transformar el objeto userEntity a un objeto user de spring security

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolesEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionEntities().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList
        );

    }


    public AuthResponse LoginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.getUsername();
        String password = authLoginRequest.getPassword();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "User logged successfully", accessToken, true);

    }

    public Authentication authenticate(String username, String password) {

        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());

    }


    public AuthResponse createUser(AuthCreateUser authCreateUser) {



    }
}
