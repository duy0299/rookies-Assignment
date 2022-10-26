package com.rookies.assignment.security.userpincal;

import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private IUserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("UserDetailsService => loadUserByUsername  1");
        Optional<UserInfo> optional = Optional.ofNullable(repository.findByEmail(email));
        System.out.println("UserDetailsService => loadUserByUsername  2");
        if(optional.isEmpty()){
            new UsernameNotFoundException("Không tìm thấy User");
        }
        System.out.println("UserDetailsService => loadUserByUsername  3");
        UserInfo  userInfo = optional.get();
        System.out.println("UserDetailsService => loadUserByUsername  4");
        return UserPrinciple.build(userInfo);
    }
}
