package com.certificateQuery.util;

import java.io.PrintWriter;
import java.io.StringWriter;

@SuppressWarnings("serial")
public class ExcelException extends Exception {
	private String message;

	public ExcelException(String message) {
		this.message = message;
	}

	public String getExcelException() {
		return message;
	}
}
