package com.rookies.assignment.serviceImpl;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IRoleRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.user.UserRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdatePasswordDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdateRoleDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.AmazonClient;
import com.rookies.assignment.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

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
    UserInfo expectedUser;
    UserRequestUpdatePasswordDto dto;
    UserInfoDtoFlat userInfoDtoFlat;
    UserRequestUpdateRoleDto requestUpdateRoleDto;
    HttpServletRequest request;
    UserRequestUpdateAvatarDto requestAvatarDto;
    @BeforeEach
    void beforeEach(){
        initiaUser = new UserInfo();
        expectedUser = new UserInfo();
        dto = new UserRequestUpdatePasswordDto();
        request = Mockito.mock(HttpServletRequest.class);
        requestUpdateRoleDto = new UserRequestUpdateRoleDto();
        requestAvatarDto = new UserRequestUpdateAvatarDto();
    }

    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenParamsIsEmptyString(){
        initiaUser = new UserInfo();
        dto =
                new UserRequestUpdatePasswordDto("a", "","");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("Cố thông tin bị rổng",exception.getMessage());
    }
    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenPasswordConfirmationFalse(){
        initiaUser = new UserInfo();
        dto =
                new UserRequestUpdatePasswordDto("c", "a","b");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("Mật khẩu xác nhận lại không giống với mật khẩu mới",exception.getMessage());
    }
    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenOldPasswordDifferentNewPassword(){
        initiaUser = new UserInfo();
        initiaUser.setPassword("c");
        dto =
                new UserRequestUpdatePasswordDto("a", "b","b");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("mật khẩu củ không đúng",exception.getMessage());
    }
    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenPasswordNotPatternMatches(){
        initiaUser = new UserInfo();
        initiaUser.setPassword("a");
        dto =
                new UserRequestUpdatePasswordDto("a", "b","b");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("Mật khẩu mới không phù hợp với yêu cầu. Phải có ít nhất 1 số từ 1-9, 1 chữ hoa, 1 chữ thường và dài từ 8-20 ký tự",exception.getMessage());
    }

    @Test
    void validateUpdate_ShouldThrowParamNotValidException_WhenParamsIsEmptyString(){
        userInfoDtoFlat = new UserInfoDtoFlat("nam", "", "1123123", "", "", true);
        userInfoDtoFlat.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Optional<UserInfo> optional = Optional.of(initiaUser);

        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdate(optional, userInfoDtoFlat));
        Assertions.assertEquals("đang có thông tin rỗng",exception.getMessage());
    }
    @Test
    void validateUpdate_ShouldThrowParamNotValidException_WhenGenderNotMaleOrFemale(){
        userInfoDtoFlat = new UserInfoDtoFlat("nam", "A", "1123123", "GAY", "A",  true);
        userInfoDtoFlat.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Optional<UserInfo> optional = Optional.of(initiaUser);

        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdate(optional, userInfoDtoFlat));
        Assertions.assertEquals("Giới tính phải là Nam hoặc Nữ",exception.getMessage());
    }
    @Test
    void validateUpdate_ShouldThrowParamNotValidException_WhenEmailNotValid(){
        userInfoDtoFlat = new UserInfoDtoFlat("nam", "A", "1123123", "Nam", "A",  true);
        userInfoDtoFlat.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Optional<UserInfo> optional = Optional.of(initiaUser);

        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdate(optional, userInfoDtoFlat));
        Assertions.assertEquals("Email Không hợp lệ",exception.getMessage());
    }
    @Test
    void validateUpdate_ShouldThrowParamNotValidException_WhenPhoneNumberNotValid(){
        userInfoDtoFlat = new UserInfoDtoFlat("nam", "A", "11231c23", "Nam", "aa@gmail.com",  true);
        userInfoDtoFlat.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Optional<UserInfo> optional = Optional.of(initiaUser);

        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdate(optional, userInfoDtoFlat));
        Assertions.assertEquals("số điện thoại không phù hợp",exception.getMessage());
    }

    @Test
    void updateRole_ShouldThrowParamNotValidException_WhenRoleNotValid(){
//        Set data
        List<String> role = new ArrayList<>();
        role.add("123a");role.add("ADMIN");
        requestUpdateRoleDto.setListRole(role);
        requestUpdateRoleDto.setUserID(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));

//        When
        Mockito.when(repository.findById(any(UUID.class))).thenReturn(Optional.of(initiaUser));
        Mockito.when(roleRepository.findAll()).thenReturn(new ArrayList<>());

//        =>
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.updateRole(requestUpdateRoleDto));
        Assertions.assertEquals("Role không hợp lệ",exception.getMessage());
    }
    @Test
    void updateRole_ShouldResponseDtoWithStatusCode200_WhenParamsIsValid(){
//        Set data
        List<String> role = new ArrayList<>();
        role.add("ADMIN");
        requestUpdateRoleDto.setListRole(role);
        requestUpdateRoleDto.setUserID(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        List<Role> listRole = new ArrayList<>();
        Role rol1 = new Role(null, null, null, "ADMIN", "ASD", (short) 1);
        Role rol2 = new Role(null, null, null, "USER", "ASD", (short) 1);
        listRole.add(rol1);
        listRole.add(rol2);
//        When
        Mockito.when(repository.findById(any(UUID.class))).thenReturn(Optional.of(initiaUser));
        Mockito.when(roleRepository.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(repository.save(initiaUser)).thenReturn(expectedUser);
//        =>
        ResponseDto actual = userService.updateRole(requestUpdateRoleDto);
        Assertions.assertEquals(200, actual.getStatusCode());
    }

    @Test
    void getByToken_ShouldThrowForbiddenException_WhenNotFoundToken(){

        Mockito.when(jwtProvider.getUserIFromHttpServletRequest(any(HttpServletRequest.class))).thenReturn(null);

        ForbiddenException exception = Assertions.assertThrows(ForbiddenException.class,
                ()->userService.getByToken( request));
        Assertions.assertEquals("Bạn Chưa đăng nhập",exception.getMessage());
    }
    @Test
    void getByToken_ShouldThrowForbiddenException_WhenFindTokenButNotFoundEmailInDatabase(){

        Mockito.when(jwtProvider.getUserIFromHttpServletRequest(any(HttpServletRequest.class))).thenReturn("b@gmail.com");
        Mockito.when(repository.findByEmail("a@gmail.com")).thenReturn(initiaUser);

        ResourceFoundException exception = Assertions.assertThrows(ResourceFoundException.class,
                ()->userService.getByToken(request));
        Assertions.assertEquals("Không tìm thấy User này",exception.getMessage());
    }
    @Test
    void getByToken_ShouldResponseDtoWithStatusCode200_WhenRequestValid(){

        Mockito.when(jwtProvider.getUserIFromHttpServletRequest(any(HttpServletRequest.class))).thenReturn("a@gmail.com");
        Mockito.when(repository.findByEmail("a@gmail.com")).thenReturn(initiaUser);

        ResponseDto actual = userService.getByToken(request);
        Assertions.assertEquals(200,actual.getStatusCode());
    }

    @Test
    void updateAvatar_ShouldThrowForbiddenException_WhenNotFoundToken(){

        Mockito.when(jwtProvider.getUserIFromHttpServletRequest(any(HttpServletRequest.class))).thenReturn(null);

        ForbiddenException exception = Assertions.assertThrows(ForbiddenException.class,
                ()->userService.updateAvatar(requestAvatarDto, request));
        Assertions.assertEquals("Bạn Chưa đăng nhập",exception.getMessage());
    }
    @Test
    void updateAvatar_ShouldThrowForbiddenException_WhenFindTokenButNotFoundEmailInDatabase(){
        Mockito.when(jwtProvider.getUserIFromHttpServletRequest(any(HttpServletRequest.class))).thenReturn("b@gmail.com");
        Mockito.when(repository.findByEmail("a@gmail.com")).thenReturn(initiaUser);

        ResourceFoundException exception = Assertions.assertThrows(ResourceFoundException.class,
                ()->userService.updateAvatar(requestAvatarDto, request));
        Assertions.assertEquals("Không tìm thấy User này",exception.getMessage());
    }

    @Test
    void updateAvatar_ShouldResponseDtoWithStatusCode200_WhenRequestValid() throws IOException {
        Mockito.when(jwtProvider.getUserIFromHttpServletRequest(any(HttpServletRequest.class))).thenReturn("a@gmail.com");
        Mockito.when(repository.findByEmail("a@gmail.com")).thenReturn(initiaUser);
        Mockito.when(repository.save(initiaUser)).thenReturn(expectedUser);
        Mockito.when(amazonClient.uploadFile(any(MultipartFile.class), anyString())).thenReturn("newAvatar.jpg...");

        ResponseDto actual = userService.updateAvatar(requestAvatarDto, request);
        Assertions.assertEquals(200,actual.getStatusCode());
    }
}
