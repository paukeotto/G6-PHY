package g06.ecnu.heartbridge.controller;

import g06.ecnu.heartbridge.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  Controller 类
 * </p>
 *
 * @author Tennsai Minamoto
 * @since 2025-03-12
 */
@Controller
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/user/register")
    public ResponseEntity register(@RequestParam String username,@RequestParam String phone, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, @RequestParam int agree){
        if (agree == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"请阅读并同意相关协议\"}");
        } else if (!password.equals(confirmPassword)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"两次密码不一致\"}");
        } else {
            return authService.register(username, password, phone, email);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestParam String id, @RequestParam String password){
        return authService.login(id, password);
    }

    @PostMapping("/admin/login")
    public ResponseEntity loginAdmin(@RequestParam String username, @RequestParam String password){
        return authService.loginAdmin(username, password);
    }
}
