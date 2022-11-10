package com.rookies.assignment.security.userpincal;

import com.rookies.assignment.data.entity.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class UserPrinciple implements UserDetails {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean gender;
    private String email;
    private String avatar;
    private String password;
    private boolean status;
    private Timestamp timeCreate;
    private Timestamp timeUpdate;
    private Collection<Rating> listRatings;
    private Collection<Feedback> listFeedbacks;
    private Collection<Wishlist> listWishlists;
    private Collection<Order> listOrder;
    private Collection<? extends GrantedAuthority> listRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return listRole;
    }

    public UserPrinciple(UUID id, String firstName, String lastName, String phoneNumber, boolean gender, String email, String avatar, String password, boolean status, Timestamp timeCreate, Timestamp timeUpdate, Collection<Rating> listRatings, Collection<Feedback> listFeedbacks, Collection<Wishlist> listWishlists, Collection<Order> listOrder, Collection<? extends GrantedAuthority> listRole) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
        this.status = status;
        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
        this.listRatings = listRatings;
        this.listFeedbacks = listFeedbacks;
        this.listWishlists = listWishlists;
        this.listOrder = listOrder;
        this.listRole = listRole;

    }

//    dễ lỗi  Hibernate could not initialize proxy – no Session
    public static UserPrinciple build(UserInfo user){
//        List<GrantedAuthority> listAuthority = user.getListUserRole().stream().map(role -> new SimpleGrantedAuthority(role.getRole().getName())).collect(Collectors.toList());
        List<GrantedAuthority> listAuthority = new ArrayList<>();
        for (Role role:user.getListRole()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            listAuthority.add(grantedAuthority);
        }
        return new UserPrinciple(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.isGender(),
                user.getEmail(),
                user.getAvatar(),
                user.getPassword(),
                user.isStatus(),
                user.getTimeCreate(),
                user.getTimeUpdate(),
                user.getListRatings(),
                user.getListFeedbacks(),
                user.getListWishlists(),
                user.getListOrder(),
                listAuthority
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
