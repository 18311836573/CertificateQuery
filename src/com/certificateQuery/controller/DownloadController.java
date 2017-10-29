package com.certificateQuery.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.certificateQuery.dto.PdfHelper;
import com.certificateQuery.model.WorkBean;
import com.certificateQuery.service.impl.WorkServiceImpl;
import com.certificateQuery.util.PasswordEncode;

@Controller
public class DownloadController {
	@Resource
	private WorkServiceImpl workService;

	
	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request, String number) throws IOException {
		WorkBean work = workService.selectWorkByNumber(number);
		
		String filepath = request.getSession().getServletContext().getRealPath(File.separator + "download");
		String pdfpath = request.getSession().getServletContext().getRealPath(File.separator + "pdfdemo");
		String fontpath = request.getSession().getServletContext().getRealPath(File.separator + "font");
		
		PdfHelper pdfHelper = new PdfHelper();
		List<WorkBean> workList = new ArrayList<WorkBean>();
		workList.add(work);
		System.out.println("调用生成pdf函数");
		pdfHelper.createPdfs(filepath, pdfpath, fontpath, workList);
		
		String filename = request.getServletContext().getRealPath("/download") + "\\" + PasswordEncode.md5Encode(number)
				+ ".pdf";
		File file = new File(filename);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", filename);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
}
