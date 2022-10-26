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
        System.out.println("configure AuthenticationManagerBuilder 1");
        auth.userDetailsService(userDetailsService).passwordEncoder( passwordEncoder() );
        System.out.println("configure AuthenticationManagerBuilder 2");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        System.out.println("authenticationManager 1");
        return super.authenticationManager();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configure HttpSecurity Begin");
        http.cors().and().csrf().disable()
//                 /** => tất cả đều truy cập đc
//                .authorizeRequests().antMatchers(HttpMethod.GET,"/product", "/products","/size", "/sizes", "/login").permitAll()
                .authorizeRequests().anyRequest().permitAll()
                .and().exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//User
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ADMIN","USER", "USER_MANAGER")
                .antMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/user").hasAnyAuthority("ADMIN", "USER_MANAGER")
                .antMatchers(HttpMethod.PUT, "/user/status").hasAnyAuthority("ADMIN", "USER_MANAGER")
                .antMatchers(HttpMethod.PUT, "/user/info").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/user/password").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/user/roles").hasAnyAuthority("ADMIN", "USER_MANAGER")
                .antMatchers(HttpMethod.PUT, "/user/avatar").hasAnyAuthority("USER");
//Product
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/user").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/product").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product/status").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product/avatar").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER");
//product-model
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/product-model").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/product-model").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product-model/info").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product-model/status").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/product-model/images").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER");
//Cart
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/cart/**",  "/cart").hasAnyAuthority( "USER")
                .antMatchers(HttpMethod.GET, "/carts").hasAnyAuthority( "USER");

//categories
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/category").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/category").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/category").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER");

//size
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/size").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.POST, "/size").hasAnyAuthority("ADMIN", "WAREHOUSE_MANAGER")
                .antMatchers(HttpMethod.PUT, "/size").hasAnyAuthority("ADMIN","WAREHOUSE_MANAGER");

        http.authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/contact",  "/cart").hasAnyAuthority( "ADMIN");

//Feedback - Rating - Wishlist
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/feedback", "/rating" , "/wishlist").hasAnyAuthority("ADMIN", "FEEDBACK_MANAGER")
                .antMatchers(HttpMethod.GET, "/feedbacks", "/ratings", "/wishlists").hasAnyAuthority("ADMIN","FEEDBACK_MANAGER")
                .antMatchers(HttpMethod.DELETE, "/feedback", "/rating", "/wishlist").hasAnyAuthority("ADMIN", "FEEDBACK_MANAGER")
                .antMatchers(HttpMethod.POST, "/feedback", "/rating", "/wishlist").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/feedback/status", "/rating/status", "/wishlist/status").hasAnyAuthority("ADMIN","FEEDBACK_MANAGER");

//Order
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/feedback", "/rating" , "/wishlist").hasAnyAuthority("ADMIN", "FEEDBACK_MANAGER")
//                .antMatchers(HttpMethod.GET, "/feedbacks", "/ratings", "/wishlists").hasAnyAuthority("ADMIN","FEEDBACK_MANAGER")
//                .antMatchers(HttpMethod.DELETE, "/feedback", "/rating", "/wishlist").hasAnyAuthority("ADMIN", "FEEDBACK_MANAGER")
//                .antMatchers(HttpMethod.POST, "/feedback", "/rating", "/wishlist").hasAnyAuthority("USER")
//                .antMatchers(HttpMethod.PUT, "/feedback/status", "/rating/status", "/wishlist/status").hasAnyAuthority("ADMIN","FEEDBACK_MANAGER");

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        System.out.println("configure HttpSecurity End");
    }

}
