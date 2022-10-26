package com.rookies.assignment.security.jwt;

import com.rookies.assignment.security.userpincal.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//      class tìm Token bên trong request
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println("JwtTokenFilter => doFilterInternal Begin 1");
            String token = getJwt(request);
            System.out.println("JwtTokenFilter => doFilterInternal 2");
            if(token != null && jwtProvider.validateToken(token)){
                String email = jwtProvider.getEmailFromToken(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );

//                đính token lên web
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                xác nhận 1 class ở trong bảo mật
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            logger.error("Can't set user authentication -> message: " + e);
        }
        System.out.println("JwtTokenFilter =>  doFilterInternal => filterChain Begin 1");
        filterChain.doFilter(request, response);
        System.out.println("JwtTokenFilter =>  doFilterInternal =>  filterChain End 2 ");
        System.out.println("JwtTokenFilter => doFilterInternal  End");
    }


//    Lấy token trong request
    private  String getJwt(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
