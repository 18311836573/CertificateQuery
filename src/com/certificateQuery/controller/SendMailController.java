package com.certificateQuery.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.certificateQuery.service.impl.MailServiceImpl;
import com.certificateQuery.util.PasswordEncode;

@Controller
public class SendMailController {
	@Resource
	private MailServiceImpl mailService;

	@RequestMapping(value = "/sendMail")
	public @ResponseBody String sendmail(String number, String receiveMailAccount, HttpServletRequest request) {
		System.out.println("调用sendMail "+number);
		
		String filepath = request.getServletContext().getRealPath("/download");
		String filename = filepath + File.separator + PasswordEncode.md5Encode(number) + ".pdf";
		try {
			mailService.sendMail(filename, number, receiveMailAccount);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"result\":\"false\"}";
		}
		return "{\"result\":\"true\"}";
	}
}
