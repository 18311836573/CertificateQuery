package test.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Test;

import com.certificateQuery.util.ExcelException;


public class Exception {
	@Test
	public void main() {
		String errorResult = "";
		try {
			throw new ExcelException("ÖÐÎÄÂÒÂë");
		} catch (ExcelException e) {
			 errorResult += e.getExcelException();
			 System.out.println(errorResult);
		}
		} 
	
	public static String getErrorInfoFromException(Exception e) { 
		StringWriter sw = new StringWriter(); 
		PrintWriter pw = new PrintWriter(sw); 
		return "\r\n" + sw.toString() + "\r\n"; 
	
		} 
}
