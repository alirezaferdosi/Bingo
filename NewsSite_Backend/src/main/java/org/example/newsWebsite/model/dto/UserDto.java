package org.example.newsWebsite.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@ToString
@Getter
public class UserDto {
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$", message = "Username must be at least 3 characters long and contain only letters, digits, '.', '_', or '-'.")
    private String username;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Phone number must be between 10 to 15 digits and may include a leading '+'.")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email must be valid.")
    private String email;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, one special character (!@#$%^&*), and must be at least 8 characters long.")
    private String password;

    private String photoPath;

    public UserDto(Long id,
                   @NonNull String username,
                   @NonNull String phone,
                   @NonNull String email,
                   @NonNull String password,
                   String photoPath) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.photoPath = photoPath;
    }
}
