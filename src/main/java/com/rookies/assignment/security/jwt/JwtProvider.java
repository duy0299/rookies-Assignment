package com.rookies.assignment.security.jwt;

import com.rookies.assignment.security.userpincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String jwtSecret;
    private int jwtExpiration = 1000 * 60;

//    Tạo Token
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
//                email
                .setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
//                .setClaims("", userPrinciple.getAuthorities())
                .setExpiration( new Date( new Date().getTime() + jwtExpiration*60*10  ))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

//    check xem token có tồn tại không.
    public boolean validateToken(String token){
        try {
            System.out.println("JwtProvider => validateToken  Begin");
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            System.out.println("JwtProvider => validateToken TRUE End");
            return true;
        }catch (SignatureException e){
            logger.error("Ivalid JWT signature -> message: "+ e);
        }catch (MalformedJwtException e){
            logger.error("The Token invalid format -> message: " + e);
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT Token -> message: " + e);
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT Token -> message: " + e);
        }catch (IllegalArgumentException e){
            logger.error("JWT claim String is empty -> message: " + e);
        }
        System.out.println("JwtProvider => validateToken FALSE End");
        return false;
    }


//    lấy Email dựa vào token
    public String getEmailFromToken(String token) {
        String email = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return email;
    }

    private  String getJwt(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
    public String getUserIFromHttpServletRequest(HttpServletRequest request){
        String token = getJwt(request);
        if(token != null && validateToken(token)){
            String email = getEmailFromToken(token);
            return email;
        }
        return null;
    }

}
