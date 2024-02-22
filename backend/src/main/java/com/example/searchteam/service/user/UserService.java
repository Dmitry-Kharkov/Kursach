package com.example.searchteam.service.user;

import com.example.searchteam.dto.request.user.*;
import com.example.searchteam.dto.request.util.EmailSendRequest;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.domain.user.UserDomainService;
import com.example.searchteam.service.domain.util.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.searchteam.controller.user.UserController.USER_RESET_PASSWORD;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService service;
    private final MailSender mailSender;

    public UserResponse getUserById(UserRequest request ){
        return service.getUserById(request.getUserId());
    }
    public UserResponse getUUIDById(UserRequest request ){
        return service.getUserById(request.getUserId());
    }

    public List<UserResponse> getAllUsers(){
        return service.getAllUsers();
    }

    public void setUUID(ResetPasswordRequest request){
        var code = java.util.UUID.randomUUID();
        service.setUUIDByLogin(request.getLogin(), code);

        var user = service.getUserByLogin(request.getLogin());

        mailSender.sendEmail(
                new EmailSendRequest()
                        .setTo(Collections.singletonList(user.getEmail()))
                        .setText("Ваш UUID для сброса пароля:"+code+"\nСсылка для смены пароля: http://localhost:8070" + USER_RESET_PASSWORD)
                        .setSubject("Смена пароля")
        );

    }

    public UserResponse addUser(UserAddRequest request) {
        if(!verificationPassword(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Некорректный пароль");
        }
        Long userId = service.addUser(request);
        service.setUserRole(userId, List.of(2L));
        return service.getUserById(userId);
    }


    public UserResponse editUser(UserAddRequest request) {
        Long userId = service.editUser(request);
        return service.getUserById(userId);
    }

    public UserResponse editPasswordUser(UserEditPasswordRequest request) {
        if(!verificationPassword(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Некорректный пароль");
        }
        Long userId = service.editPasswordUser(request);
        return service.getUserById(userId);
    }

    public void resetPassword(ResetPasswordRequest request){
        if(!verificationPassword(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Некорректный пароль");
        }
        service.resetPassword(request);
    }

    public UserResponse editRolesUser(UserEditRolesRequest request) {
        Long userId = service.editRolesUser(request);
        return service.getUserById(userId);
    }

    public Boolean isExists(LoginUserRequest request) {
        return service.isExists(request);
}

    public List<UserResponse> searchUsers(FiltrationUser request) {
        return service.getAllUsers()
                .stream()
                .filter(e->e.getLogin().contains(request.getSearchValue().toLowerCase()))
                .skip(request.getFrom())
                .limit(request.getCount())
                .toList();
    }

    private boolean verificationPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Matcher m =  Pattern.compile(regex).matcher(password);
        return (m.matches());
    }


}