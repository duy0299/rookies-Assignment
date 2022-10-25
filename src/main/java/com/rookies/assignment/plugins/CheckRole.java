package com.rookies.assignment.plugins;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.dto.flat.RoleDtoFlat;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Component
public class  CheckRole {

    public  boolean checkLevelUser(int level, HttpSession session){
        UserInfoResponseDto login = (UserInfoResponseDto) session.getAttribute("login");
        if(login == null){
            return false;
        }
        for (RoleDtoFlat role : login.getListRole()) {
            if(role.getLevel() == level){
                return true;
            }
        }
        return false;
    }

    public  boolean checkLevelUser(int level1, int level2, HttpSession session){
        UserInfoResponseDto login = (UserInfoResponseDto) session.getAttribute("login");
        if(login == null){
            return false;
        }
        for (RoleDtoFlat role : login.getListRole()) {
            if(role.getLevel() == level1 && role.getLevel() == level2){
                return true;
            }
        }
        return false;
    }

    public  boolean isUserLogged (UUID userID, HttpSession session){
        UserInfoResponseDto login = (UserInfoResponseDto) session.getAttribute("login");
        if(login == null){
            return false;
        }
        if (login.getId().equals(userID)){
            return true;
        }
        return false;
    }
}
