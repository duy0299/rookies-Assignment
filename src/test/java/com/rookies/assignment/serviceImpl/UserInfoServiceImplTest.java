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
        Assertions.assertEquals("C??? th??ng tin b??? r???ng",exception.getMessage());
    }
    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenPasswordConfirmationFalse(){
        initiaUser = new UserInfo();
        dto =
                new UserRequestUpdatePasswordDto("c", "a","b");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("M???t kh???u x??c nh???n l???i kh??ng gi???ng v???i m???t kh???u m???i",exception.getMessage());
    }
    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenOldPasswordDifferentNewPassword(){
        initiaUser = new UserInfo();
        initiaUser.setPassword("$2y$10$9.JeIXVO3K4pQPBfEy8IauB9mJ656VGsJAK9Sb1sguJ1BWCgywVmo");
        dto =
                new UserRequestUpdatePasswordDto("bA1234567", "aa","aa");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("m???t kh???u c??? kh??ng ????ng",exception.getMessage());
    }
    @Test
    void validateUpdatePassword_ShouldThrowParamNotValidException_WhenPasswordNotPatternMatches(){
        initiaUser = new UserInfo();
        initiaUser.setPassword("$2y$10$9.JeIXVO3K4pQPBfEy8IauB9mJ656VGsJAK9Sb1sguJ1BWCgywVmW");
        dto =
                new UserRequestUpdatePasswordDto("bA1234567", "bbb","bbb");
        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdatePassword(initiaUser, dto));
        Assertions.assertEquals("M???t kh???u m???i kh??ng ph?? h???p v???i y??u c???u. Ph???i c?? ??t nh???t 1 s??? t??? 1-9, 1 ch??? hoa, 1 ch??? th?????ng v?? d??i t??? 8-20 k?? t???",exception.getMessage());
    }

    @Test
    void validateUpdate_ShouldThrowParamNotValidException_WhenParamsIsEmptyString(){
        userInfoDtoFlat = new UserInfoDtoFlat("nam", "", "1123123", "", "", true);
        userInfoDtoFlat.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Optional<UserInfo> optional = Optional.of(initiaUser);

        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdate(optional, userInfoDtoFlat));
        Assertions.assertEquals("??ang c?? th??ng tin r???ng",exception.getMessage());
    }
    @Test
    void validateUpdate_ShouldThrowParamNotValidException_WhenGenderNotMaleOrFemale(){
        userInfoDtoFlat = new UserInfoDtoFlat("nam", "A", "1123123", "GAY", "A",  true);
        userInfoDtoFlat.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Optional<UserInfo> optional = Optional.of(initiaUser);

        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdate(optional, userInfoDtoFlat));
        Assertions.assertEquals("Gi???i t??nh ph???i l?? Nam ho???c N???",exception.getMessage());
    }
    @Test
    void validateUpdate_ShouldThrowParamNotValidException_WhenEmailNotValid(){
        userInfoDtoFlat = new UserInfoDtoFlat("nam", "A", "1123123", "Nam", "A",  true);
        userInfoDtoFlat.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Optional<UserInfo> optional = Optional.of(initiaUser);

        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdate(optional, userInfoDtoFlat));
        Assertions.assertEquals("Email Kh??ng h???p l???",exception.getMessage());
    }
    @Test
    void validateUpdate_ShouldThrowParamNotValidException_WhenPhoneNumberNotValid(){
        userInfoDtoFlat = new UserInfoDtoFlat("nam", "A", "11231c23", "Nam", "aa@gmail.com",  true);
        userInfoDtoFlat.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        Optional<UserInfo> optional = Optional.of(initiaUser);

        ParamNotValidException exception = Assertions.assertThrows(ParamNotValidException.class,
                ()->userService.validateUpdate(optional, userInfoDtoFlat));
        Assertions.assertEquals("s??? ??i???n tho???i kh??ng ph?? h???p",exception.getMessage());
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
        Assertions.assertEquals("Role kh??ng h???p l???",exception.getMessage());
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
        Assertions.assertEquals("B???n Ch??a ????ng nh???p",exception.getMessage());
    }
    @Test
    void getByToken_ShouldThrowForbiddenException_WhenFindTokenButNotFoundEmailInDatabase(){
        Mockito.when(jwtProvider.getUserIFromHttpServletRequest(any(HttpServletRequest.class))).thenReturn("b@gmail.com");
        Mockito.when(repository.findByEmail("a@gmail.com")).thenReturn(initiaUser);

        ResourceFoundException exception = Assertions.assertThrows(ResourceFoundException.class,
                ()->userService.getByToken(request));
        Assertions.assertEquals("Kh??ng t??m th???y User n??y",exception.getMessage());
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
        Assertions.assertEquals("B???n Ch??a ????ng nh???p",exception.getMessage());
    }
    @Test
    void updateAvatar_ShouldThrowForbiddenException_WhenFindTokenButNotFoundEmailInDatabase(){
        Mockito.when(jwtProvider.getUserIFromHttpServletRequest(any(HttpServletRequest.class))).thenReturn("b@gmail.com");
        Mockito.when(repository.findByEmail("a@gmail.com")).thenReturn(initiaUser);

        ResourceFoundException exception = Assertions.assertThrows(ResourceFoundException.class,
                ()->userService.updateAvatar(requestAvatarDto, request));
        Assertions.assertEquals("Kh??ng t??m th???y User n??y",exception.getMessage());
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
