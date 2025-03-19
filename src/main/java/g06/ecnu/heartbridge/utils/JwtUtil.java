package g06.ecnu.heartbridge.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final long EXPIRATION_TIME = 86400000; // 1 天
    private static final SecretKey key = Jwts.SIG.HS256.key().build();

    // 生成 JWT
    public static String generateToken(String username, int userType) {
        return Jwts.builder()
                .subject(username)
                .claim("userType", userType)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // 解析 JWT
    public static Claims parseToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims;
        }
        catch (Exception e) {
            System.err.println("Error parsing jwt: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // 验证 JWT 是否有效
    public static boolean validateToken(String token, String username, int userType) {
        try {
            Claims claims = parseToken(token);
            if (claims != null) {
                String tokenUsername = claims.getSubject();
                int tokenUserType = claims.get("userType", int.class);
                boolean isExpired = claims.getExpiration().before(new Date());
                return (userType == tokenUserType) && (username.equals(tokenUsername) && !isExpired);
            }
            else return false;
        }
        catch (Exception e) {
            return false;
        }
    }
}
