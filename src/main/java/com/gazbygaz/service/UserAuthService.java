package com.gazbygaz.service;

import com.gazbygaz.common.MasterData;
import com.gazbygaz.dto.UserRegistrationDto;
import com.gazbygaz.entity.Role;
import com.gazbygaz.entity.User;
import com.gazbygaz.exceptions.InvalidRequestException;
import com.gazbygaz.repository.RoleRepository;
import com.gazbygaz.repository.UserRepository;
import com.gazbygaz.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserAuthService extends CommonService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Response registerUser(UserRegistrationDto registrationDto) {
        // Check if the user already exists
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.name(), "User already exists");
        }

        // Find the role
        Role role = (Role) roleRepository.findByName(registrationDto.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        // Create and save the user
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);
        Response response = new Response();

        response.setStatus(HttpStatus.OK.name());
        response.setCode(String.valueOf(HttpStatus.OK.value()));
        response.setMessage(getMessageForResponseCode(MasterData.ResponseCode.USER_REG_SUCCESS_CODE, null));
        return response;
    }
}
