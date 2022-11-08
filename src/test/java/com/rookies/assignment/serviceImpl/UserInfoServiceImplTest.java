package com.rookies.assignment.serviceImpl;

import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IRoleRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.request.user.UserRequestUpdatePasswordDto;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.AmazonClient;
import com.rookies.assignment.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserInfoServiceImplTest {

    @Autowired
    UserInfoServiceImpl userService;
    @MockBean
    IUserInfoRepository repository;
    @MockBean
    IRoleRepository roleRepository;
    @MockBean
    AmazonClient amazonClient;
    @MockBean
    JwtProvider jwtProvider;

    UserInfo initiaUser;

    @BeforeEach
    void beforeEach(){
        UserRequestUpdatePasswordDto dto = new UserRequestUpdatePasswordDto();
        dto.setPassword("a");
        dto.setNewPassword("aa");
        dto.setPasswordConfirmation("");

    }

    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenParamsIsEmptyString(){
        initiaUser = new UserInfo();
        UserRequestUpdatePasswordDto dto =
                new UserRequestUpdatePasswordDto("a", "","");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("Cố thông tin bị rổng",exception.getMessage());
    }

    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenPasswordConfirmationFalse(){
        initiaUser = new UserInfo();
        UserRequestUpdatePasswordDto dto =
                new UserRequestUpdatePasswordDto("c", "a","b");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("Mật khẩu xác nhận lại không giống với mật khẩu mới",exception.getMessage());
    }

    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenOldPasswordDifferentNewPassword(){
        initiaUser = new UserInfo();
        initiaUser.setPassword("c");
        UserRequestUpdatePasswordDto dto =
                new UserRequestUpdatePasswordDto("a", "b","b");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("mật khẩu củ không đúng",exception.getMessage());
    }

    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenPasswordNotPatternMatches(){
        initiaUser = new UserInfo();
        initiaUser.setPassword("a");
        UserRequestUpdatePasswordDto dto =
                new UserRequestUpdatePasswordDto("a", "b","b");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("Mật khẩu mới không phù hợp với yêu cầu. Phải có ít nhất 1 số từ 1-9, 1 chữ hoa, 1 chữ thường và dài từ 8-20 ký tự",exception.getMessage());
    }

}
