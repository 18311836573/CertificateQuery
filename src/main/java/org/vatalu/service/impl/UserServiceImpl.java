package org.vatalu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vatalu.map.UserMapper;
import org.vatalu.model.entity.CommonUser;
import org.vatalu.model.entity.User;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.LoginResponse;
import org.vatalu.model.response.QueryResponse;
import org.vatalu.model.response.RegisterResponse;
import org.vatalu.service.UserService;
import org.vatalu.util.PasswordEncode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginResponse login(String userName, String password, HttpSession session) {
        User user = userMapper.findByName(userName);
        if (user != null || !(PasswordEncode.md5Encode(password + user.getSalt())).equals(user.getPassword())) {
            session.setAttribute("level", user.getLevel());
            session.setAttribute("userName", userName);
            return new LoginResponse(true, userName, user.getLevel());
        }
        return new LoginResponse(false);
    }

    @Override
    public LoginResponse register(String userName, String password, String level) {
        String salt = PasswordEncode.createsalt();
        if (userMapper.findByName(userName) == null && userMapper.insert(userName, PasswordEncode.md5Encode(password + salt), level, salt) == 1) {
            return new LoginResponse(true, userName, level);
        } else {
            return new LoginResponse(false);
        }
    }

    @Override
    public CommonResponse deleteUser(String userName) {
        if (userMapper.deleteByName(userName) == 1) {
            return new CommonResponse(true);
        } else {
            return new CommonResponse(false);
        }
    }

    @Override
    public CommonResponse updateUser(String userName, String password) {
        String salt = PasswordEncode.createsalt();
        if (userMapper.updatePasswordByName(userName, PasswordEncode.md5Encode(password + salt), salt) == 1) {
            return new CommonResponse(true);
        } else {
            return new CommonResponse(false);
        }
    }

    @Override
    public QueryResponse<CommonUser> queryUsers(Integer page, Integer size) {
        List<CommonUser> userList = userMapper.findUsers(page * size, size);
        if (userList.size() != 0) {
//            List<CommonUser> commonUsers = new ArrayList<>();
//            for (User user : userList) {
//                commonUsers.add(new CommonUser(user));
//            }
            return new QueryResponse<>(true, userList.size(), userList);
        } else {
            return new QueryResponse<>(false);
        }
    }
}

