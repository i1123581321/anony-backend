/*
 * File Created: 2021/12/02 19:55:25
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/12/07 10:35:13
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private final int jwtExpiration;
    private final SecretKey key;

    public JwtUtils(@Value("${anony.jwt.secret}") String secret, @Value("${anony.jwt.expiration}") int jwtExpiration){
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtExpiration = jwtExpiration;
    }

    public String generateJwtToken(Authentication authentication) {
        var user = (UserDetails) authentication.getPrincipal();
        var date = new Date();
        var expiration = new Date(date.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(date)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String generateJwtToken(String username){
        var date = new Date();
        var expiration = new Date(date.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void validate(String token) {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
