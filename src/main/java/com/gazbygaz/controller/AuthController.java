package com.gazbygaz.controller;

import com.gazbygaz.common.MasterData;
import com.gazbygaz.dto.AuthDto;
import com.gazbygaz.dto.UserRegistrationDto;
import com.gazbygaz.exceptions.InvalidRequestException;
import com.gazbygaz.response.Response;
import com.gazbygaz.security.JwtUtil;
import com.gazbygaz.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = MasterData.GAZ_BY_GAZ_V1+"/auth/admin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userService;

    @PostMapping("/login")
    public Response login(@RequestBody AuthDto authDto) {
        Response response = new Response();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
            System.out.println("cvbgh "+authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", userDetails.getAuthorities());
            response.setAuthToken(jwtUtil.generateToken(authDto.getUsername(), claims));
            response.setStatus(HttpStatus.OK.name());
            response.setCode(String.valueOf(HttpStatus.OK.value()));
            response.setMessage("User login success!");
            return response;

        } catch (AuthenticationException e) {
            throw new InvalidRequestException(HttpStatus.FORBIDDEN.name(), HttpStatus.FORBIDDEN.value(), e.getMessage(), null);
        }
    }

    @PostMapping("/register")
    public Response registerUser(@RequestBody UserRegistrationDto registrationDto) {
        return userService.registerUser(registrationDto);
    }
}
