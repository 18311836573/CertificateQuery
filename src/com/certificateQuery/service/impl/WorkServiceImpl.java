package com.certificateQuery.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.certificateQuery.dao.WorkDao;
import com.certificateQuery.dto.ExcelHelper;
import com.certificateQuery.model.WorkBean;
import com.certificateQuery.service.WorkService;
import com.certificateQuery.util.ExcelFilter;
import com.certificateQuery.util.PasswordEncode;
import com.certificateQuery.dto.PdfHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WorkServiceImpl implements WorkService {
	@Resource
	private WorkDao workdao;

	@Override
	public List<WorkBean> selectWork(String year, String workId, String workName, String school, String authorName) {
		String[] studentArray = authorName.split(" ", 3);
		String student1 = null, student2 = null, student3 = null;
		student1 = studentArray[0];
		if (studentArray.length > 2) {
			student2 = studentArray[1];
			student3 = studentArray[2];
		} else if (studentArray.length == 2) {
			student2 = studentArray[1];
		}
		System.out.println("year:" + year + " workid:" + workId + "workname:" + workName + " school:" + school
				+ " student1:" + student1 + " student2" + student2 + " student3" + student3);
		List<WorkBean> list = workdao.findWork(year, workId, workName, school, student1, student2, student3);
		System.out.println(list.size());
		return list;
	}

	@Override
	public WorkBean selectWorkByNumber(String number) {
		return workdao.findWorkByNumber(number);
	}

	@Override
	public int insertWorks(InputStream is, String year, String month, String area, List<WorkBean> list) throws Exception {
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.importXlsx(is, year, month, area, list);
		int resultNum = workdao.insertWorks(list);
		if (resultNum != list.size()) {
			return 0;
		}
		return resultNum;
	}

	// @Override
	// public int updateWorks(InputStream is, String year, String month, String
	// area, List<WorkBean> list) {
	// ExcelHelper excelHelper = new ExcelHelper();
	// try {
	// excelHelper.importXlsx(is, year, month, area, list);
	// } catch (Exception e) {
	// e.printStackTrace();
	// return 0;
	// }
	// int resultNum = 0;
	// for (WorkBean work : list) {
	// if (workdao. != null) { //查找的标准有问题
	// resultNum += workdao.updateWork(work);
	// } else {
	// resultNum += workdao.insertWork(work);
	// }
	// }
	// if (resultNum != list.size()) {
	// return 0;
	// }
	// return resultNum;
	// }

	@Override
	public int updateWork(WorkBean work) {
		return workdao.updateWork(work);
	}

	@Override
	public int insertWork(WorkBean work) {
		Long number = workdao.findMaxNumber();
		System.out.println(number);
		work.setNumber((++number).toString());
		return workdao.insertWork(work);

	}

	@Override
	public int deleteWork(List<WorkBean> list) {
		return workdao.deleteWork(list);
	}

}
