package com.certificateQuery.service;

import java.util.List;

import com.certificateQuery.model.Certificate;

public interface CertificateService {
	public List<Certificate> selectCertificates (int start);
	public int updateCertificate(String number, String isJudged);
	public int insertCertificate(Certificate certificate);
}
