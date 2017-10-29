package com.certificateQuery.dto;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.certificateQuery.model.WorkBean;
import com.certificateQuery.util.PasswordEncode;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PdfHelper {
	private static final String source = "/certificate.pdf";
	private static final String FONT1 = "/方正综艺简体.ttf";
	private static final String FONT2 = "/方正小标宋_GBK.TTF";

	public void createPdfs(String filepath, String pdfpath, String fontpath, List<WorkBean> list) {
		for (int i = 0; i < list.size(); i++) {
			WorkBean work = list.get(i);
			System.out.println(work.getNumber());
			String filename = filepath + File.separator + PasswordEncode.md5Encode(work.getNumber()) + ".pdf";
			System.out.println(filename);
			File file = new File(filename);
			if (file.exists() && file.isDirectory()) {
				System.out.println("文件已经存在");
				continue;
			}
			createPdf(filename, pdfpath, fontpath, work);
			System.out.println("创建pdf成功");
		}
	}

	synchronized public void createPdf(String filename, String pdfpath, String fontpath, WorkBean work) {
		PdfWriter writer;
		PdfReader reader;
		try {
			writer = new PdfWriter(filename);
			reader = new PdfReader(pdfpath + source);
			PdfDocument pdf = new PdfDocument(reader, writer);
			Document document = new Document(pdf);
			PdfFont font1 = PdfFontFactory.createFont(fontpath + FONT1, PdfEncodings.IDENTITY_H, false);
			PdfFont font2 = PdfFontFactory.createFont(fontpath + FONT2, PdfEncodings.IDENTITY_H, false);

			Paragraph p1 = new Paragraph(work.getWorkname());
			p1.setFont(font2);
			p1.setFontSize(17);
			p1.setFixedPosition(305, 353, 315);
			document.add(p1);

			Paragraph p2 = new Paragraph(String.valueOf(work.getYear()));
			p2.setFont(font2);
			p2.setFontSize(30);
			p2.setFixedPosition(672, 341, 100);
			document.add(p2);

			int i = 8;
			i += work.getYear() - 2017;
			Paragraph p3 = new Paragraph(String.valueOf(i));
			p3.setFont(font2);
			p3.setFontSize(30);
			p3.setFixedPosition(231, 304, 20);
			document.add(p3);

			String level = work.getLevel();
			String c = String.valueOf(level.charAt(0));
			Paragraph p4 = new Paragraph(c);
			p4.setFont(font2);
			p4.setFontSize(27);
			p4.setFixedPosition(208, 270, 100);
			document.add(p4);

			Paragraph p5 = new Paragraph(work.getStudent1() + " " + work.getStudent2() + " " + work.getStudent3());
			p5.setFont(font2);
			p5.setFontSize(21);
			p5.setFixedPosition(219, 235, 300);
			document.add(p5);

			Paragraph p6 = new Paragraph(work.getTeacher1() + " " + work.getTeacher2());
			p6.setFont(font2);
			p6.setFontSize(21);
			p6.setFixedPosition(235, 200, 300);
			document.add(p6);

			String t = work.getYear() + "年" + work.getMonth() + "月" + "   " + work.getArea();
			Paragraph p7 = new Paragraph(t);
			p7.setFont(font1);
			p7.setFontSize(23);
			p7.setFixedPosition(480, 30, 250);
			document.add(p7);

			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
