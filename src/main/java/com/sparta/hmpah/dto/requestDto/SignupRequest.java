package com.sparta.hmpah.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

@Getter
@Setter
public class SignupRequest extends LoginInfoRequest {

    @NotBlank

    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;


}
