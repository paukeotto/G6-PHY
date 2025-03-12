package g06.ecnu.heartbridge.controller;

import g06.ecnu.heartbridge.entity.AuthResponseEntity;
import g06.ecnu.heartbridge.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseEntity register(@RequestParam String username, @RequestParam String password){
        return authService.register(username, password);
    }

    @PostMapping("/login")
    public AuthResponseEntity login(@RequestParam String username, @RequestParam String password){
        return authService.login(username, password);
    }
}
