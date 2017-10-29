package com.certificateQuery.service;

import com.certificateQuery.model.AdminBean;

public interface AdminService {
	
	public AdminBean selectAdmin(String username);
	
	public int insertAdmin(AdminBean admin);
	
	public int deleteAdmin(AdminBean admin);
	
	public int updateAdmin(AdminBean admin);
}
