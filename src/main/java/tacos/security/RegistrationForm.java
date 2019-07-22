package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import tacos.User;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String phoneNumber;

    public User toUser(PasswordEncoder pwdEncoder) {
        return new User(username, pwdEncoder.encode(password), 
        fullname, street, city, state, phoneNumber);
        
    }

}