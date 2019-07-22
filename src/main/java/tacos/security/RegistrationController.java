package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String registerForm(){
        return "register";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form){
        System.out.println(form);
        userRepo.save(form.toUser(pwdEncoder));
        return "redirect:/login";
    }


    
}