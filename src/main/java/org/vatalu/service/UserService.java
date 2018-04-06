package org.vatalu.service;

import org.vatalu.model.entity.CommonUser;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.LoginResponse;
import org.vatalu.model.response.QueryResponse;
import org.vatalu.model.response.RegisterResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {
    public LoginResponse login(String userName, String password, HttpSession session);

    public LoginResponse register(String userName, String password, String level);

    public CommonResponse deleteUser(String userName);

    public CommonResponse updateUser(String userName,String password);

    public QueryResponse<CommonUser> queryUsers(Integer page,Integer size);
}
