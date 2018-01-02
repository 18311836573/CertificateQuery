package com.certificateQuery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.certificateQuery.model.WorkBean;
import com.certificateQuery.model.WorkBeanList;
import com.certificateQuery.service.impl.WorkServiceImpl;
import com.certificateQuery.util.BeanToJson;
import com.certificateQuery.util.ExcelException;

/**
 * @author VATALU
 * @version 1.0
 */
@Controller
public class WorkController {
	@Resource
	private WorkServiceImpl workService;

	@RequestMapping(value = "/query", produces = "application/json; charset=utf-8")
	public @ResponseBody String query(String year, String workId, String workName, String school, String authorName) {
		System.out.println(year + workId + workName + school + authorName);
		List<WorkBean> list = workService.selectWork(year, workId, workName, school, authorName);
		return BeanToJson.WorkBeanToJson(list);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String upload(HttpServletRequest request) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setHeaderEncoding(request.getCharacterEncoding());
		List<FileItem> list;
		List<WorkBean> workList = new ArrayList<WorkBean>();
		try {
			list = upload.parseRequest(request);
			FileItem itemyear = list.get(0);
			FileItem itemmonth = list.get(1);
			FileItem itemarea = list.get(2);
			FileItem item = list.get(3);
			String year = itemyear.getString();
			String month = itemmonth.getString();
			String area = itemarea.getString("UTF-8");
			System.out.println(year + month + area);
			try {
				if (workService.insertWorks(item.getInputStream(), year, month, area, workList) == 0) {
					return "{\"result\":\"false\"}";
				}
			} catch (ExcelException e) {
				System.out.println(e.getExcelException());
				return "{\"result\":\"false\"," + "\"error\":\"" + e.getExcelException() + "\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"result\":\"false\"}";
		}
		return "{\"result\":\"OK\"}";
	}

	@RequestMapping(value = "/uploadone",method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public @ResponseBody String upload(WorkBean work) {
		System.out.println(work.getLevel());
		int result = workService.insertWork(work);
		if (result == 0) {
			return "{\"result\":\"false\"}";
		} else {
			return "{\"result\":\"true\"}";
		}
	}

	// @SuppressWarnings("unchecked")
	// @RequestMapping(value = "/update")
	// public @ResponseBody String update(HttpServletRequest request) {
	// FileItemFactory factory = new DiskFileItemFactory();
	// ServletFileUpload upload = new ServletFileUpload(factory);
	// upload.setHeaderEncoding("UTF-8");
	// upload.setHeaderEncoding(request.getCharacterEncoding());
	// List<FileItem> list;
	// List<WorkBean> workList = new ArrayList<WorkBean>();
	// try {
	// list = upload.parseRequest(request);
	// FileItem itemyear = list.get(0);
	// FileItem itemmonth = list.get(1);
	// FileItem itemarea = list.get(2);
	// FileItem item = list.get(3);
	// String year = itemyear.getString();
	// String month = itemmonth.getString();
	// String area = itemarea.getString("UTF-8");
	// System.out.println(year + month + area);
	// if (workService.updateWorks(item.getInputStream(), year, month, area,
	// workList) == 0) {
	// return "{\"result\":\"false\"}";
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// return "{\"result\":\"false\"}";
	// }
	// return "{\"result\":\"OK\"}";
	// }

	@RequestMapping(value = "/updateone",method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public @ResponseBody String update(WorkBean work) {
		System.out.println(work.getNumber());
		if (workService.updateWork(work) == 0) {
			return "{\"result\":\"false\"}";
		} else {
			return "{\"result\":\"true\"}";
		}
	}

	@RequestMapping(value = "/delete",produces = "application/json; charset=utf-8")
	public @ResponseBody String delete(String number) {
		if (workService.deleteWork(number) != 1) {
			return "{\"result\":\"false\"}";
		} else {
			return "{\"result\":\"true\"}";
		}
	}

	@RequestMapping(value = "/queryWorks",produces = "application/json; charset=utf-8")
	public @ResponseBody String works(int offset,int limit,String oder) {
		List<WorkBean> list = workService.selectWorks(offset);
		return BeanToJson.WorkBeanToJson(list);
	}
}
