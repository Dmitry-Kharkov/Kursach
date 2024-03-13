package com.example.searchteam.controller.user;

import com.example.searchteam.config.MailFactory;
import com.example.searchteam.dto.request.user.FiltrationUser;
import com.example.searchteam.dto.request.user.LoginUserRequest;
import com.example.searchteam.dto.request.user.ResetPasswordRequest;
import com.example.searchteam.dto.request.user.UserAddRequest;
import com.example.searchteam.dto.request.user.UserEditPasswordRequest;
import com.example.searchteam.dto.request.user.UserEditRolesRequest;
import com.example.searchteam.dto.request.user.UserRequest;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
/**
 * Контроллер для пользователя
 */
@Tag(name = "Управление пользователями")
public class UserController {

    public static final String USER_GET_BY_ID = "/api/v1/user/get-by-id";
    public static final String USER_GET_ALL = "/api/v1/user/get-all";
    public static final String USER_EDIT = "/api/v1/user/edit";
    public static final String USER_EDIT_PASSWORD = "/api/v1/user/edit-pwd";
    public static final String USER_ADD = "/api/v1/user/add";
    public static final String USER_EDIT_ROLES="/api/v1/user/edit-roles";
    public static final String USER_SEARCH="/api/v1/user/search";
    public static final String USER_LOGIN="/api/v1/user/login";
    public static final String SEND_UUID="/api/v1/user/send";
    public static final String USER_RESET_PASSWORD="/api/v1/user/reset-password";

    private final UserService service;
    private final MailFactory mailFactory;


    @Operation(summary = "Получение пользователя по ID")
    /**
     * Запрос получения пользователя по id
     * @param request - userId
     * @return пользователь
     */
    @PostMapping(
            value = USER_GET_BY_ID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@RequestBody UserRequest request){
        return service.getUserById(request);
    }

    /**
     * Запрос добавления пользователя
     * @param request - UserAddRequest(id,name,login,password)
     * @return пользователь
     */
    @PostMapping(
            value = USER_ADD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse addUser(@RequestBody UserAddRequest request) {
        return service.addUser(request);
    }

    /**
     * Запрос изменения пользователя
     * @param request - UserAddRequest(id,name,login,password)
     * @return пользователь
     */
    @PostMapping(
            value = USER_EDIT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse editUser(@RequestBody UserAddRequest request) {
        return service.editUser(request);
    }

    /**
     * Запрос изменения пароля пользователя
     * @param request - UserEditPasswordRequest(id,password)
     * @return пользователь
     */
    @PostMapping(
            value = USER_EDIT_PASSWORD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse editPasswordUser(@RequestBody UserEditPasswordRequest request) {
        return service.editPasswordUser(request);
    }

    /**
     * Запрос изменения роли пользователя
     * @param request - UserEditRolesRequest(id,roles)
     * @return пользователь
     */
    @PostMapping(
            value = USER_EDIT_ROLES,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public UserResponse editRolesUser(@RequestBody UserEditRolesRequest request) {
        return service.editRolesUser(request);
    }

    /**
     * Запрос получения всех пользователей
     * @return список пользователей
     */
    @PostMapping(
            value = USER_GET_ALL,
            produces = APPLICATION_JSON_VALUE)
    public List<UserResponse> getAllUsers(){ return service.getAllUsers(); }

    /**
     * Запрос проверки существования пользователя
     * @param request - LoginUserRequest(login,password)
     * @return булевое значение
     */
    @PostMapping(
            value = USER_LOGIN,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public Boolean isExists(@RequestBody LoginUserRequest request){ return service.isExists(request); }

    /**
     * Запрос поиска пользователя
     * @param request - FiltrationUser(searchValue,from,count)
     * @return список пользователей
     */
    @PostMapping(
            value = USER_SEARCH,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public List<UserResponse> searchUsers(@RequestBody FiltrationUser request){ return service.searchUsers(request); }

    @PostMapping(
            value = USER_RESET_PASSWORD,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public void resetPassword(@RequestBody ResetPasswordRequest request){ service.resetPassword(request); }


    @PostMapping(
            value = SEND_UUID,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public void sendUUID(@RequestBody  ResetPasswordRequest request){

        service.setUUID(request);

    }


}