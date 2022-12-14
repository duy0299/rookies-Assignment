package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IRoleRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.user.UserRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdatePasswordDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdateRoleDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.AmazonClient;
import com.rookies.assignment.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoRepository repository;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AmazonClient amazonClient;
    @Value("${amazonProperties.folderSaveUser}")
    private String folderName;
    @Autowired
    private JwtProvider jwtProvider;

//    Update info of User first name, last name, phone, gender
    @Override
    public ResponseDto<UserInfoResponseDto> update(UserInfoDtoFlat dto, HttpServletRequest request) {
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        if(email==null){
            throw new ForbiddenException("B???n Ch??a ????ng nh???p");
        }

        Optional<UserInfo> optional = Optional.ofNullable(repository.findByEmail(email));
//        Validate
        validateUpdate(optional, dto);
        UserInfo modified = repository.save(dto.changeToUserInfoUpdate(optional.get()));
        return new ResponseDto<>(new UserInfoResponseDto(modified));
    }

    @Override
    public ResponseDto<UserInfoResponseDto> delete(UUID id) {
        Optional<UserInfo> user = repository.findById(id);
        if(user.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
        repository.delete(user.get());
        return new ResponseDto<>(null);
    }

    @Override
    public ResponseDto<UserInfoResponseDto> updateStatus(UUID id, boolean status) {
        Optional<UserInfo> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
        optional.get().setStatus(status);

        UserInfo modified = repository.save(optional.get());
        return new ResponseDto<>(new UserInfoResponseDto(modified));
    }

    @Override
    public ResponseDto<UserInfoResponseDto> getById(UUID id) {
        Optional<UserInfo> user = repository.findById(id);
        if(user.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
        return new ResponseDto<>(new UserInfoResponseDto(user.get()));
    }

    @Override
    public ResponseDto<UserInfoResponseDto> getByToken(HttpServletRequest request) {
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        if(email==null){
            throw new ForbiddenException("B???n Ch??a ????ng nh???p");
        }
        Optional<UserInfo> user = Optional.ofNullable(repository.findByEmail(email));
        if(user.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
        return new ResponseDto<>(new UserInfoResponseDto(user.get()));
    }

    @Override
    public ResponseByPageDto<List<UserInfoResponseDto>> listAll(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<Page<UserInfo>> optional = Optional.ofNullable(repository.findAll(pageable));
        List<UserInfoResponseDto> listResult = new ArrayList<>();
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
//        UserInfo => Convert to UserInfoResponseDto => Add to listResult
        for (UserInfo userInfo: optional.get()) {
            listResult.add(new UserInfoResponseDto(userInfo));
        }
        return new ResponseByPageDto<>(optional.get().getTotalPages(), listResult);
    }

    @Override
    public ResponseDto<UserInfoResponseDto> updatePassword(UserRequestUpdatePasswordDto dto, HttpServletRequest request) {
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        if(email==null){
            throw new ForbiddenException("B???n Ch??a ????ng nh???p");
        }
        Optional<UserInfo> user = Optional.ofNullable(repository.findByEmail(email));
        if(user.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
        validateUpdatePassword(user.get(), dto);
        dto.setNewPassword(passwordEncoder.encode(dto.getNewPassword()));
        user.get().setPassword(dto.getNewPassword());
        repository.save(user.get());
        return new ResponseDto<>(new UserInfoResponseDto(user.get()));
    }


//    admin add or remove Role for User
    @Override
    public ResponseDto<UserInfoResponseDto> updateRole(UserRequestUpdateRoleDto dto) {
        Optional<UserInfo> userOptional = repository.findById(dto.getUserID());
        Optional<List<Role>> listRoleOptional = Optional.ofNullable(roleRepository.findAll());
        List<Role> listRole = new ArrayList<>();
        if(userOptional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
        if(listRoleOptional.isEmpty()){
            throw new ResourceFoundException("danh s??ch Role R???ng");
        }

        for (String nameRole : dto.getListRole()) {
            if(!nameRole.equals("BAN") && !nameRole.equals("ADMIN") && !nameRole.equals("USER") &&
                    !nameRole.equals("BAN_COMMENT") && !nameRole.equals("FEEDBACK_MANAGER") && !nameRole.equals("WAREHOUSE_MANAGER") &&
                    !nameRole.equals("USER_MANAGER")&& !nameRole.equals("ORDER_MANAGER")){
                throw new ParamNotValidException("Role kh??ng h???p l???");
            }

            for (Role role : listRoleOptional.get()) {
                if(nameRole.equals(role.getName())){
                    listRole.add(role);
                    break;
                }
            }
        }
        userOptional.get().setListRole(listRole);
        repository.save(userOptional.get());
        return new ResponseDto<>(new UserInfoResponseDto(userOptional.get()));
    }

    @Override
    public ResponseDto<UserInfoResponseDto> updateAvatar(UserRequestUpdateAvatarDto dto, HttpServletRequest request) {
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        String urlAvatar = "";
        if(email==null){
            throw new ForbiddenException("B???n Ch??a ????ng nh???p");
        }
        Optional<UserInfo> user = Optional.ofNullable(repository.findByEmail(email));
        if(user.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
        //      up file image to Amazon s3
        try {
            urlAvatar     = amazonClient.uploadFile(dto.getFileAvatar(), folderName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        UserInfo modifiedUser =  repository.save(
                dto.changeToProduct( user.get(), urlAvatar)
        );
        return new ResponseDto<UserInfoResponseDto>(new UserInfoResponseDto(modifiedUser));
    }





    public void validateUpdate(Optional<UserInfo> optional, UserInfoDtoFlat dto){
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User n??y");
        }
        if(dto.getLastName().trim().equals("") || dto.getFirstName().trim().equals("") || dto.getPhoneNumber().trim().equals("") ){
            throw new ParamNotValidException("??ang c?? th??ng tin r???ng");
        }
        if(!dto.getGender().toLowerCase().trim().equals("nam") && !dto.getGender().toLowerCase().trim().equals("n???")
                && !dto.getGender().toLowerCase().trim().equals("male") && !dto.getGender().toLowerCase().trim().equals("female")  ){
            throw new ParamNotValidException("Gi???i t??nh ph???i l?? Nam ho???c N???");
        }
        if(!Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", dto.getEmail())) {
            throw new ParamNotValidException("Email Kh??ng h???p l???");
        }
        if(!Pattern.matches("^([0-9]{8,14})$", dto.getPhoneNumber())) {
            throw new ParamNotValidException("s??? ??i???n tho???i kh??ng ph?? h???p");
        }
    }


    public void validateUpdatePassword(UserInfo user, UserRequestUpdatePasswordDto dto){
        if(dto.getPassword().trim().equals("") || dto.getPasswordConfirmation().trim().equals("") ||
                dto.getNewPassword().trim().equals("")){
            throw new ParamNotValidException("C??? th??ng tin b??? r???ng");
        }

        if(!dto.getNewPassword().equals(dto.getPasswordConfirmation()) ){
            throw new ParamNotValidException("M???t kh???u x??c nh???n l???i kh??ng gi???ng v???i m???t kh???u m???i");
        }

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new ParamNotValidException("m???t kh???u c??? kh??ng ????ng");
        }
        //	check  condition 1 uppercase, 1 lowercase, 1 number, from 8-20 characters
        if(!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,20})$", dto.getNewPassword())) {
            throw new ParamNotValidException("M???t kh???u m???i kh??ng ph?? h???p v???i y??u c???u. Ph???i c?? ??t nh???t 1 s??? t??? 1-9, 1 ch??? hoa, 1 ch??? th?????ng v?? d??i t??? 8-20 k?? t???");
        }
    }
}
