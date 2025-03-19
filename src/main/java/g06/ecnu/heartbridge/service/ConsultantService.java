package g06.ecnu.heartbridge.service;

import g06.ecnu.heartbridge.mapper.UsersMapper;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Service
 * </p>
 *
 * @author Tennsai Minamoto
 * @since 2025/3/19
 */
@Service
public class ConsultantService {
    @Resource
    private UsersMapper usersMapper;

    /*
        TODO: 获取可预约时间
     */
    public ResponseEntity<Object> getAvailableTimes(int consultantId) {
        Map<String, List<String>> response = new HashMap<>();
        List<String> availableTimes = usersMapper.getAvailableTimes();
        return ResponseEntity.ok(response);
    }
}
