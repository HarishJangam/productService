package com.capstone.springprojecta.commons;

import com.capstone.springprojecta.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthenticationCommons {
    private  RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public UserDto validateToken(String token){
        ResponseEntity<UserDto> userDto=restTemplate.postForEntity("http://localhost:8181/users/validate/"+token,
                null,
                UserDto.class);
        if(userDto.getBody()==null){
            return null;
        }
        return userDto.getBody();
    }
}
