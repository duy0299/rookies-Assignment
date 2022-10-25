package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IRoleRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.request.LoginRequestDto;
import com.rookies.assignment.dto.request.RegisterRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.RepeatDataException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private IUserInfoRepository repository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public ResponseDto<UserInfoResponseDto> login(LoginRequestDto dto, HttpServletRequest req) {
        Optional<UserInfo> user = Optional.ofNullable(repository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()));
        if(user.isEmpty()){
            throw new ResourceFoundException("Mật khẩu hoặc Email Không đúng");
        }
        ResponseDto<UserInfoResponseDto> result = new ResponseDto<UserInfoResponseDto>("00","thành công",
                                                                 new UserInfoResponseDto(user.get()));
        req.getSession().setAttribute("login",result);
        return result;
    }

    @Override
    public ResponseDto<UserInfoResponseDto> logout(HttpServletRequest req) {
        req.getSession().setAttribute("login",null);
        return new ResponseDto<UserInfoResponseDto>("thành công");
    }

    @Override
    public ResponseDto<UserInfoResponseDto> register(RegisterRequestDto dto, HttpServletRequest req) {
        //  check empty
        if(dto.getFirstName().trim().equals("") || dto.getLastName().trim().equals("") || dto.getPhoneNumber().trim().equals("") ||
                dto.getEmail().trim().equals("") || dto.getPassword().trim().equals("") || dto.getPasswordConfirmation().trim().equals("")) {
            String error= "không thể để trống";
            throw new ParamNotValidException("Có thông tin bị trống");
        }

        //  check password confirmation
        if(!dto.getPassword().equals(dto.getPasswordConfirmation())) {
            throw new ParamNotValidException("Mật khẩu không khớp với nhau");
        }

        //	check  condition 1 uppercase, 1 lowercase, 1 number, from 8-20 characters
        if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,20})$", dto.getPassword())) {
            throw new ParamNotValidException("Mật khẩu không phù hợp với yêu cầu");
        }
        //	check xem email này có chưa
        Optional<UserInfo> user = Optional.ofNullable(repository.findByEmail(dto.getEmail()));
        if(!user.isEmpty()){
            throw new RepeatDataException("Email này đã tồn tại");
        }
//      get role user
        Role role = roleRepository.findByLevel((short) 2);

//      create new user
        UserInfo newUser = dto.changeToUserInfo(role);

//       save UserInfo
        repository.save(newUser);

        ResponseDto<UserInfoResponseDto> result = new ResponseDto<UserInfoResponseDto>("00","thành công",
                new UserInfoResponseDto(newUser));
        req.getSession().setAttribute("login",result);
        return result;
    }
}
