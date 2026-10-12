package org.example.pr7.security;

import org.example.pr7.model.RolesModel;
import org.example.pr7.model.UsersModel;
import org.example.pr7.repository.RolesRepository;
import org.example.pr7.repository.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl
        implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    public UserDetailsServiceImpl(
            UsersRepository usersRepository,
            RolesRepository rolesRepository
    ) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {

        UsersModel user = usersRepository
                .findFirstByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(username)
                );

        RolesModel role = rolesRepository
                .findById(user.getRoleId())
                .orElseThrow(
                        () -> new RuntimeException("Role not found")
                );

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_" + role.getName())
                .build();
    }
}