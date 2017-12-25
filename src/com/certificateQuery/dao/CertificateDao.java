package com.certificateQuery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.certificateQuery.model.Certificate;
import com.certificateQuery.model.WorkBean;

@Repository
public interface CertificateDao {
	@Transactional(readOnly=true,rollbackFor=Exception.class,timeout=6000,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public List<Certificate> findCertificates(@Param("start")Integer start,@Param("num")Integer num);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=6000,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int updateCertificate(String number, String isJudged);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=6000,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int insertCertificate(Certificate certificate);
}
