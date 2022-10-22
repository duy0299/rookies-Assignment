package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.entity.UserRole;
import com.rookies.assignment.data.repository.IRoleRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.data.repository.IUserRoleRepository;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.UserRequestDto;
import com.rookies.assignment.dto.request.UserRequestUpdatePasswordDto;
import com.rookies.assignment.dto.request.UserRequestUpdateRoleDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

//    Update info of User first name, last name, phone, gender
    @Override
    public ResponseDto<UserInfoResponseDto> update(UserInfoDtoFlat dto) {
        Optional<UserInfo> optional = repository.findById(dto.getId());
//        Validate
        validateUpdate(optional, dto);
        UserInfo modified = repository.save(dto.changeToUserInfoUpdate(optional.get()));
        return new ResponseDto(new UserInfoResponseDto(modified));
    }

    @Override
    public ResponseDto<UserInfoResponseDto> delete(UUID id) {
        Optional<UserInfo> user = repository.findById(id);
        if(user.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy User này");
        }
        repository.delete(user.get());
        return new ResponseDto(null);
    }

    @Override
    public ResponseDto<UserInfoResponseDto> updateStatus(UUID id) {
        Optional<UserInfo> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy User này");
        }
        if(optional.get().isStatus()){
            optional.get().setStatus(false);
        }else{
            optional.get().setStatus(true);
        }
        UserInfo modified = repository.save(optional.get());
        return new ResponseDto(modified);
    }

    @Override
    public ResponseDto<UserInfoResponseDto> getById(UUID id) {
        Optional<UserInfo> user = repository.findById(id);
        if(user.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy User này");
        }
        return new ResponseDto<>(new UserInfoResponseDto(user.get()));
    }

    @Override
    public ResponseDto<List<UserInfoResponseDto>> listAll() {
        Optional<List<UserInfo>> user = Optional.ofNullable(repository.findAll());
        List<UserInfoResponseDto> listResult = new ArrayList<>();
        if(user.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy User này");
        }
//        UserInfo => Convert to UserInfoResponseDto => Add to listResult
        for (UserInfo userInfo: user.get()) {
            listResult.add(new UserInfoResponseDto(userInfo));
        }
        return new ResponseDto<>(listResult);
    }

    @Override
    public ResponseDto<UserInfoResponseDto> updatePassword(UserRequestUpdatePasswordDto dto) {
        Optional<UserInfo> user = repository.findById(dto.getUser_id());
        validateUpdatePassword(user, dto);
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
            throw new ResourceFoundException("Không tìm thấy User này");
        }
        if(listRoleOptional.isEmpty()){
            throw new ResourceFoundException("danh sách Role Rỗng");
        }
        for (Short level : dto.getListRoleLevel()) {
            if(level<0 || level > 7){
                throw new ParamNotValidException("level của role không hợp lệ");
            }
            System.out.println("test 5");
            for (Role role : listRoleOptional.get()) {
                if(level == role.getLevel()){
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
    public ResponseDto<UserInfoResponseDto> updateAvatar(UUID id, MultipartFile fileAvatar) {
        return null;
    }


    public void validateUpdate(Optional<UserInfo> optional, UserInfoDtoFlat dto){
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy User này");
        }
        if(dto.getLastName().trim().equals("") || dto.getFirstName().trim().equals("") || dto.getPhoneNumber().trim().equals("") ){
            throw new ParamNotValidException("đang có thông tin rỗng");
        }
        if(!dto.getGender().toLowerCase().trim().equals("nam") && !dto.getGender().toLowerCase().trim().equals("nữ")
                && !dto.getGender().toLowerCase().trim().equals("male") && !dto.getGender().toLowerCase().trim().equals("female")  ){
            throw new ParamNotValidException("Giới tính phải là Nam hoặc Nữ");
        }
        if(!Pattern.matches("^([0-9]{8,14})$", dto.getPhoneNumber())) {
            throw new ParamNotValidException("số điện thoại không phù hợp");
        }
    }

    public void validateUpdateStatus(Optional<UserInfo> optional, UserRequestDto dto){
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy User này");
        }
        if(!dto.isStatus() ){
            throw new ParamNotValidException("Giới tính phải là Nam hoặc Nữ");
        }
        if(!Pattern.matches("^([0-9]{8,14})$", dto.getPhoneNumber())) {
            throw new ParamNotValidException("số điện thoại không phù hợp");
        }
    }

    public void validateUpdatePassword(Optional<UserInfo> optional, UserRequestUpdatePasswordDto dto){
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy User này");
        }
        if(!dto.getNewPassword().equals(dto.getPasswordConfirmation()) ){
            throw new ParamNotValidException("Mật khẩu xác nhận lại không giống với mật khẩu mới ");
        }
        if(!dto.getPassword().equals(optional.get().getPassword()) ){
            throw new ParamNotValidException("mật khẩu củ không đúng");
        }
        //	check  condition 1 uppercase, 1 lowercase, 1 number, from 8-20 characters
        if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,20})$", dto.getPassword())) {
            throw new ParamNotValidException("Mật khẩu mới không phù hợp với yêu cầu. Phải có ít nhất 1 số từ 1-9, 1 chữ hoa, 1 chữ thường và dài từ 8-20 ký tự");
        }
    }
}
