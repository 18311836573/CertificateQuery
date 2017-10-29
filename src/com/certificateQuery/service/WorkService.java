package com.certificateQuery.service;

import java.io.InputStream;
import java.util.List;

import com.certificateQuery.model.WorkBean;

public interface WorkService {

	public List<WorkBean> selectWork(String year, String workId, String  workName, String school, String authorName);
	
	public WorkBean selectWorkByNumber(String number);
	
	public int insertWorks(InputStream is, String year, String month, String area, List<WorkBean> list) throws Exception;
	
//	public int updateWorks(InputStream is, String year, String month, String area, List<WorkBean> list);
	
	public int updateWork(WorkBean work);
	
	public int insertWork(WorkBean work);
	
	public int deleteWork(List<WorkBean> work);
}
