package g06.ecnu.heartbridge.service;

import g06.ecnu.heartbridge.entity.AuthResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public AuthResponseEntity register(String username, String password){
        return new AuthResponseEntity(null);
    }

    public AuthResponseEntity login(String username, String password){
        return new AuthResponseEntity(null);
    }
}
