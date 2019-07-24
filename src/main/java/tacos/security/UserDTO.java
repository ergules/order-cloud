package tacos.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import tacos.User;

@Data
public class UserDTO {

    @NotNull
    @Size(min = 4, max = 11)
    private String username;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;
    private String confirm;

    @NotBlank(message = "required")
    private String fullname;

    @NotBlank(message = "required")
    private String street;

    @NotBlank(message = "required")
    private String city;
    @NotBlank(message = "required")
    private String state;
    @NotBlank(message = "required")
    private String phone;

    public User toUser(PasswordEncoder pwdEncoder) {
        return new User(username, pwdEncoder.encode(password), fullname, street, city, state, phone);

    }

}