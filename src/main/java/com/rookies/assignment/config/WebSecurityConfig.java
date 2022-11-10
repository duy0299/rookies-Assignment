package com.rookies.assignment.config;

import com.rookies.assignment.security.jwt.JwtEntryPoint;
import com.rookies.assignment.security.jwt.JwtTokenFilter;
import com.rookies.assignment.security.userpincal.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder( passwordEncoder() );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//User
        http.authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/user/{id}/status").hasAnyAuthority("ADMIN", "USER_MANAGER")
                .antMatchers(HttpMethod.PUT, "/user/{id}/roles").hasAnyAuthority("ADMIN", "USER_MANAGER")
                .antMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ADMIN", "USER_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/user/{id}").hasAnyAuthority("ADMIN", "USER_MANAGER");

//Product
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/product").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/product").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product/{id}").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product/{id}/status").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product/{id}/avatar").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER");
//product-model
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/model/{id}").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/model").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/model/with-products").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/model/{id}/info").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/model/{id}/status").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/model/{id}/images").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER");


//categories
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/category/{id}").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/category").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/category/{id}").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER");

//size
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/size/{id}").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/size").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/size/{id}").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER");

//Feedback - Rating - Wishlist
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/feedback/{id}", "/rating/{id}" ).hasAnyAuthority("ADMIN", "FEEDBACK_MANAGER")
                .antMatchers(HttpMethod.GET, "/feedbacks", "/ratings", "/wishlists").hasAnyAuthority("ADMIN","FEEDBACK_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/feedback/{id}", "/rating/{id}").hasAnyAuthority("ADMIN", "FEEDBACK_MANAGER")
                .antMatchers(HttpMethod.PUT, "/feedback/{id}/status", "/rating/{id}/status").hasAnyAuthority("ADMIN","FEEDBACK_MANAGER")
                .antMatchers(HttpMethod.PUT, "/order/{id}/status").hasAnyAuthority("ADMIN","ORDER_MANAGER");
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}
