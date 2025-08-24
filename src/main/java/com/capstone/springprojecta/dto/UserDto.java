package com.capstone.springprojecta.dto;

import com.capstone.springprojecta.models.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private String hashPassword;
    List<RoleDto> role;
    private boolean isEmailVerified;
}
