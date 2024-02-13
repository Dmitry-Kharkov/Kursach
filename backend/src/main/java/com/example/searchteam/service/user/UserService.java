package com.example.searchteam.service.user;

import com.example.searchteam.dto.request.user.*;
import com.example.searchteam.dto.response.user.UserResponse;
import com.example.searchteam.service.domain.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor

/**
 * Сервис пользователя
 * Реализует методы обработки информации о пользователе
 */
public class UserService {

    /**
     * Domain Service пользователя
     * Реализует методы обработки информации о пользователе
     */
    private final UserDomainService service;

    /**
     * получение пользователя по id
     * @param request - id
     * @return пользователь
     */
    public UserResponse getUserById(UserRequest request ){
        return service.getUserById(request.getUserId());
    }

    /**
     * получение всех пользователей
     * @return список пользователей
     */
    public List<UserResponse> getAllUsers(){
        return service.getAllUsers();
    }

    /**
     * Создание нового пользователя
     * @param request - UserAddRequest(id,name,login,password)
     * @return пользователь
     */
    public UserResponse addUser(UserAddRequest request) {
        if(!verificationPassword(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Некорректный пароль");
        }
        Long userId = service.addUser(request);
        service.setUserRole(userId, List.of(2L));
        return service.getUserById(userId);
    }

    /**
     * Изменение пользователя
     * @param request - UserAddRequest(id,name,login,password)
     * @return пользователь
     */
    public UserResponse editUser(UserAddRequest request) {
        Long userId = service.editUser(request);
        return service.getUserById(userId);
    }

    /**
     * Изменение пароля пользователя
     * @param request - UserEditPasswordRequest(id,password)
     * @return пользователь
     */
    public UserResponse editPasswordUser(UserEditPasswordRequest request) {
        if(!verificationPassword(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Некорректный пароль");
        }
        Long userId = service.editPasswordUser(request);
        return service.getUserById(userId);
    }

    /**
     * Изменение роли пользователя
     * @param request - UserEditRolesRequest(id,roles)
     * @return пользователь
     */
    public UserResponse editRolesUser(UserEditRolesRequest request) {
        Long userId = service.editRolesUser(request);
        return service.getUserById(userId);
    }

    /**
     * Существование пользователя
     * @deprecated Проверка, существует ли пользователь
     * @param request - LoginUserRequest(login,password)
     * @return булевые значения
     */
    public Boolean isExists(LoginUserRequest request) {
        return service.isExists(request);
}

    /**
     * Поиск пользователей
     * @param request - FiltrationUser(searchValue,from,count)
     * @return список пользователей
     */
    public List<UserResponse> searchUsers(FiltrationUser request) {
        return service.getAllUsers()
                .stream()
                .filter(e->e.getLogin().contains(request.getSearchValue().toLowerCase()))
                .skip(request.getFrom())
                .limit(request.getCount())
                .toList();
    }

    /**
     * Проверка корректности пароля
     * @param password - пароль
     * @return булевое значение
     */
    private boolean verificationPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Matcher m =  Pattern.compile(regex).matcher(password);
        return (m.matches());
    }
}