package com.certificateQuery.dto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.certificateQuery.model.WorkBean;
import com.certificateQuery.util.ExcelException;
import com.certificateQuery.util.ExcelFilter;
import com.certificateQuery.util.RandomString;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelHelper {
	private static Map<String, String> citycode;
	static {
		Properties props = new Properties();
		citycode = new HashMap<String, String>();
		InputStream in = null;
		try {
			in = ExcelHelper.class.getClassLoader().getResourceAsStream("citycode.properties");
			props.load(in);
			Enumeration<?> en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String property = props.getProperty(key);
				citycode.put(key, property);
				System.out.println(key + " " + property);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean importXlsx(InputStream is, String year, String month, String area, List<WorkBean> list) throws Exception {
		Workbook wb = null; // 创建工作本

		String areacode = citycode.get(area); // 获取赛区相应的编码
		System.out.println("areacode" + areacode);
		
		wb = Workbook.getWorkbook(is); // 获取输入流
		Sheet sheet = wb.getSheet(0); // 获得第一张sheet
		
		if (sheet.getRows() >= 899999) {
			throw new Exception("数据量太大");
		}

//		try {
			for (int i = 1, subnumber = 100000; i < sheet.getRows() && subnumber < 1000000; i++, subnumber++){
				WorkBean work = new WorkBean();
				work.setYear(Integer.parseInt(year));
				work.setMonth(Integer.parseInt(month));
				work.setArea(area);
				work.setNumber(year + month + areacode + String.valueOf(subnumber));
				work.setLevel(sheet.getCell(0, i).getContents());
				work.setSchool(sheet.getCell(1, i).getContents());
				work.setWorkid(sheet.getCell(2, i).getContents());
				work.setWorkname(sheet.getCell(3, i).getContents());
				work.setStudent1(sheet.getCell(4, i).getContents());
				work.setStudent1Id(sheet.getCell(5, i).getContents());
				work.setStudent2(sheet.getCell(6, i).getContents());
				work.setStudent2Id(sheet.getCell(7, i).getContents());
				work.setStudent3(sheet.getCell(8, i).getContents());
				work.setStudent3Id(sheet.getCell(9, i).getContents());
				work.setTeacher1(sheet.getCell(10, i).getContents());
				work.setTeacher1Id(sheet.getCell(11, i).getContents());
				work.setTeacher2(sheet.getCell(12, i).getContents());
				work.setTeacher2Id(sheet.getCell(13, i).getContents());
				list.add(work);
			}
				ExcelFilter excelFilter = new ExcelFilter();
				String result = excelFilter.LengthFilter(list);
				if( result != null ) {
					throw new ExcelException(result.substring(0, result.length()-1));
				}
				excelFilter.CharFilter(list);
		return true;
	}
}
