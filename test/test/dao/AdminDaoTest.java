package test.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certificateQuery.service.impl.AdminServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  //ʹ��junit4���в���  
@WebAppConfiguration
@ContextConfiguration   
({"/spring*.xml"}) //���������ļ�

public class AdminDaoTest {
	
	@Resource
	private AdminServiceImpl adminService;
	@Test
	public void testAdminDao() {
		System.out.println("123");
	}
}
