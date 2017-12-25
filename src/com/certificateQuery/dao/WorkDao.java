package com.certificateQuery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.certificateQuery.model.WorkBean;

@Repository
public interface WorkDao {
	@Transactional(readOnly=true,rollbackFor=Exception.class,timeout=30,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public List<WorkBean> findWork(String year, String workId, String workName, String school, String student1, String student2, String student3);
	
	@Transactional(readOnly=true,rollbackFor=Exception.class,timeout=30,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public WorkBean findWorkByNumber(String number);
	
	@Transactional(readOnly=true,rollbackFor=Exception.class,timeout=30,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public Long findMaxNumber();
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=6000,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int insertWorks(List<WorkBean> list);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=6000,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int insertWork(WorkBean work);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=6000,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int deleteWork(List<WorkBean> list);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=6000,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public int updateWork(WorkBean work);
	
	@Transactional(readOnly=false,rollbackFor=Exception.class,timeout=6000,propagation = Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	public List<WorkBean> findWorks(@Param("start")Integer start,@Param("num")Integer num);
	
	
}
