package com.certificateQuery.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.certificateQuery.model.AdminBean;
import com.certificateQuery.service.impl.AdminServiceImpl;
import com.certificateQuery.util.PasswordEncode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author VATALU
 * @version 1.0
 */
@Controller
public class LogController {

	@Resource
	private AdminServiceImpl adminService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String login(String username, String password, HttpServletRequest request) {
		System.out.println(username + "\n" + password);
		AdminBean admin = adminService.selectAdmin(username);
		if (admin == null || !(PasswordEncode.md5Encode(password + admin.getSalt())).equals(admin.getPassword())) {
			return "{\"result\":\"false\"}";
		} else {
			request.getSession().setAttribute("login", "true");
			request.getSession().setAttribute("level", admin.getLevel());
			return "{\"result\":\"true\",\"account\":\"" + username + "\"}";
		}
	}

	@RequestMapping(value = "/logout", produces = "application/json; charset=utf-8")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		session.removeAttribute("level");
		System.out.println("����logout" + request.getSession().getAttribute("login"));
		return "index";
	}

	@RequestMapping(value = "/createAdmin" , method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String createAdmin(AdminBean admin, HttpServletRequest request) {
		if(judgeLevel(request)) {
			return "{\"result\":\"false\"}";
		}
		if (adminService.selectAdmin(admin.getUsername()) != null) {
			return "{\"result\":\"false\"}";
		}
		admin.setUsername(admin.getUsername());
		String salt = PasswordEncode.createsalt();
		admin.setSalt(salt);
		admin.setPassword(PasswordEncode.md5Encode(admin.getPassword() + salt));
		admin.setLevel(admin.getLevel());
		if (adminService.insertAdmin(admin) != 0) {
			return "{\"result\":\"true\"}";
		} else
			return "{\"result\":\"false\"}";
	}

	@RequestMapping(value = "/deleteAdmin", produces = "application/json; charset=utf-8")
	public @ResponseBody String deleteAdmin(String username, HttpServletRequest request) {
		if(judgeLevel(request)) {
			return "{\"result\":\"false\"}";
		}
		AdminBean admin = adminService.selectAdmin(username);
		if (admin == null) {
			return "{\"result\":\"false\"}";
		}
		if (adminService.deleteAdmin(admin) == 0) {
			return "{\"result\":\"false\"}";
		}
		return "{\"result\":\"true\"}";
	}

	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String updateAdmin(AdminBean admin, HttpServletRequest request) {
		if(judgeLevel(request)) {
			return "{\"result\":\"false\"}";
		}
		if (adminService.selectAdmin(admin.getUsername()) == null) {
			return "{\"result\":\"false\"}";
		}
		String salt = PasswordEncode.createsalt();
		admin.setSalt(salt);
		admin.setPassword(PasswordEncode.md5Encode(admin.getPassword() + salt));
		admin.setLevel(admin.getLevel());
		if (adminService.updateAdmin(admin) == 0) {
			return "{\"result\":\"false\"}";
		} else {
			return "{\"result\":\"true\"}";
		}
	}

	@RequestMapping(value = "/queryAdmin", produces = "application/json; charset=utf-8")
	public @ResponseBody String queryAdmin(String username, HttpServletRequest request) {
		if(judgeLevel(request)) {
			return "{\"result\":\"false\"}";
		}
		AdminBean admin = adminService.selectAdmin(username);
		if (admin == null)
			return "{\"result\":\"false\"}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(admin);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{\"result\":\"false\"}";
		}

	}
	private boolean judgeLevel(HttpServletRequest request) {
		String level = (String)request.getSession().getAttribute("level");
		if(level.equals("svip")) {
			return true;
		}else {
			return false;
		}
	}
}
