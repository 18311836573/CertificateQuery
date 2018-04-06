package org.vatalu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vatalu.model.entity.CommonUser;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.LoginResponse;
import org.vatalu.model.response.QueryResponse;
import org.vatalu.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    private static final String SVIP = "svip";
    private static final String VIP = "vip";
    private static final String USERNAME = "userName";
    private static final String LEVEL = "level";

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/user/login", produces = "application/json; charset=utf-8")
    public LoginResponse login(@RequestParam("userName") String userName,
                               @RequestParam("password") String password,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userService.login(userName, password, session);
    }

    @GetMapping(value = "/user/logout", produces = "application/json; charset=utf-8")
    public CommonResponse logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(USERNAME);
            session.removeAttribute(LEVEL);
            return new CommonResponse(true);
        } else {
            return new CommonResponse(false);
        }
    }

    @PostMapping(value = "/user", produces = "application/json; charset=utf-8")
    public LoginResponse register(@RequestParam("userName") String userName,
                                  @RequestParam("password") String password,
                                  @RequestParam("level") String level,
                                  @SessionAttribute("level") String sessionLevel) {
        if (sessionLevel.equals(SVIP))
            return userService.register(userName, password, level);
        else
            return new LoginResponse(false);
    }

    @DeleteMapping(value = "/user", produces = "application/json; charset=utf-8")
    public CommonResponse deleteUser(@RequestParam("userName") String userName,
                                     @SessionAttribute("level") String level) {
        if (level.equals(SVIP)) {
            return userService.deleteUser(userName);
        } else {
            return new CommonResponse(false);
        }
    }

    @PutMapping(value = "/user", produces = "application/json; charset=utf-8")
    public CommonResponse updateUser(@RequestParam("userName") String userName,
                                     @RequestParam("password") String password,
                                     @SessionAttribute("level") String level) {
        if (level.equals(SVIP) || level.equals(VIP)) {
            return userService.updateUser(userName, password);
        } else {
            return new CommonResponse(false);
        }
    }

    @GetMapping(value = "/users", produces = "application/json;charset=utf-8")
    public QueryResponse<CommonUser> queryUser(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer page,
                                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer size,
                                               @SessionAttribute("level") String level) {
        if (level.equals(SVIP)) {
            if (page < 1) page = 1;
            if (size <= 0) size = 10;
            return userService.queryUsers(page - 1, size);
        } else {
            return new QueryResponse<>(false);
        }
    }

}
