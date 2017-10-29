package com.certificateQuery.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.certificateQuery.dao.AdminDao;
import com.certificateQuery.model.AdminBean;
import com.certificateQuery.service.AdminService;
import com.certificateQuery.util.PasswordEncode;

@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminDao admindao;
	
	@Override
	public AdminBean selectAdmin(String username) {
		return admindao.selectAdmin(username);
	}

	@Override
	public int insertAdmin(AdminBean admin) {
		return admindao.insertAdmin(admin);
	}

	@Override
	public int deleteAdmin(AdminBean admin) {
		return admindao.deleteAdmin(admin);
	}

	@Override
	public int updateAdmin(AdminBean admin) {
		return admindao.updatePassword(admin);
	}

}
