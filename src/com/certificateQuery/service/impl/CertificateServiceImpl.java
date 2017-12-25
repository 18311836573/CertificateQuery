package com.certificateQuery.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.certificateQuery.dao.CertificateDao;
import com.certificateQuery.model.Certificate;
import com.certificateQuery.service.CertificateService;

@Service
public class CertificateServiceImpl implements CertificateService{
	@Resource
	private CertificateDao certificateDao;
	
	@Override
	public List<Certificate> selectCertificates(int start){
		return certificateDao.findCertificates(start, start+10);
	}

	@Override
	public int updateCertificate(String number, String isJudged) {
		return certificateDao.updateCertificate(number, isJudged);
	}

	@Override
	public int insertCertificate(Certificate certificate) {
		return certificateDao.insertCertificate(certificate);
	}
	
	
}
