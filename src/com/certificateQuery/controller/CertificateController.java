package com.certificateQuery.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.certificateQuery.model.Certificate;
import com.certificateQuery.service.impl.CertificateServiceImpl;
import com.certificateQuery.util.BeanToJson;

@Controller
public class CertificateController {
	@Resource
	private CertificateServiceImpl certificateService;
	
	@RequestMapping(value="queryCertificate", produces = "application/json; charset=utf-8")
	public @ResponseBody String getCertificates(int start) {
		List<Certificate> list = certificateService.selectCertificates(start);
		return BeanToJson.CertificateToJson(list);
	}
	
	@RequestMapping(value="judgeCertificate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String getJudgeCertificate(String workId,String isjudge) {
		if(certificateService.updateCertificate(workId, isjudge) == 1) {
			return "{\"result\":\"true\"}";
		} else {
			return "{\"result\":\"false\"}";
		}
	}
	
	@RequestMapping(value="certificate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String getCertificate(String number, String email, String address, String studentId) {
		Certificate certificate = new Certificate(number,email,address,studentId,"false");
		if(certificateService.insertCertificate(certificate) == 1) {
			return "{\"result\":\"true\"}";
		} else {
			return "{\"result\":\"false\"}";
		}
	}
}
