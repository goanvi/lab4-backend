package goanvi.web.weblab3.security.jwt;

import goanvi.web.weblab3.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtUtils {
    private final long expirationTime;
    private final Key key;
    //TODO подтягивать ключи из переменных окружения
    public JwtUtils(@Value("${jwtSecretKey}") String jwtKey,
                    @Value("${expirationTimeHour}") String expirationTime) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    jwtKey.getBytes(StandardCharsets.UTF_8));
            this.key = Keys.hmacShaKeyFor(encodedhash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        long hour = Long.parseLong(expirationTime);

        this.expirationTime = TimeUnit.HOURS.toMillis(hour);
    }

    public String extractUserId(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(CustomUserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(userDetails, claims);
    }

    public String generateToken(CustomUserDetails userDetails, Map<String, Object> claims){
        return createToken(userDetails, claims);
    }

    public boolean tokenIsValid(String token){
        return !tokenIsExpired(token);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private String createToken(CustomUserDetails userDetails, Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Long.toString(userDetails.getUserId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    private boolean tokenIsExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
