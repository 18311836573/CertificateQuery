package com.certificateQuery.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.certificateQuery.model.AdminBean;

@Repository
public interface AdminDao{
	@Transactional(readOnly=true,rollbackFor=Exception.class,timeout=30,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public AdminBean selectAdmin(String username);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=20,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int insertAdmin(AdminBean admin);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=20,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int deleteAdmin(AdminBean admin);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=20,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int updatePassword(AdminBean admin);
	
}
