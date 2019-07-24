package tacos.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.User;
import tacos.data.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder pwdEncoder;

    @Autowired
	public RegistrationController(UserRepository userRepo, PasswordEncoder pwdEncoder) {
		this.userRepo = userRepo;
		this.pwdEncoder = pwdEncoder;
    }
    
    @ModelAttribute(name = "userDTO")
    public UserDTO regForm() {
        return new UserDTO();
    }

    @GetMapping
    public String registerForm(){
        return "register";
    }

    @PostMapping
    public String processRegistration(@Valid UserDTO form, BindingResult result){
        if(!validateUser(result, form)  || result.hasErrors() ){
            return "register";
        }
        userRepo.save(form.toUser(pwdEncoder));
        return "login";
    }

    public boolean validateUser(BindingResult result, UserDTO form){
        boolean valid = true;
        if( ! form.getPassword().equals(form.getConfirm()) ){
            result.rejectValue("confirm", "password.mismatch");
            valid = false;
        }
        User user = userRepo.findByUsername(form.getUsername());
        if(user != null){
            result.rejectValue("username", "username.taken");
            valid = false;
        }
        return valid;
    }


    
}