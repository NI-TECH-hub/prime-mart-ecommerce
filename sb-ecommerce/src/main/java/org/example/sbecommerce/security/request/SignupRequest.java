package org.example.sbecommerce.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank
    @Size(min=2,max=20)
    private String username;

    @NotBlank
    @Size(max=50)
    @Email
    private String email;


    private Set<String> role;

    @NotBlank
    @Size(min=6,max=40)
    private String password;
}
