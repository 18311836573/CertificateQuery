package org.vatalu.model.dto;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.vatalu.model.entity.Work;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelToWork {

    private static Logger logger = LoggerFactory.getLogger(ExcelToWork.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    public static List<Work> readExcel(MultipartFile file) throws IOException {
        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<Work> list = new ArrayList<Work>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    Work work = getRowValue(rowNum, row);
                    list.add(work);
                }
            }
            workbook.close();
        }
        return list;
    }

    private static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if (null == file) {
            logger.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "不是excel文件");
            throw new IOException(fileName + "请选择excel文件");
        }
    }

    private static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }

    private static Work getRowValue(int rowNum, Row row) throws IOException {
        Work work = new Work();
        Cell cell1 = row.getCell(0);
        if (cell1 != null && cell1.getStringCellValue().length()==3) {
            work.setLevel(cell1.getStringCellValue());
        } else {
            throw new IOException("第" + rowNum + "行第1列数据错误");
        }
        Cell cell2 = row.getCell(1);
        if (cell2 != null && !cell2.getStringCellValue().equals("")) {
            work.setSchool(cell2.getStringCellValue());
        } else {
            throw new IOException("第" + rowNum + "行第2列数据错误");
        }
        Cell cell3 = row.getCell(2);
        if (cell3 != null && cell3.getStringCellValue().length()==3) {
            work.setWorkid(cell3.getStringCellValue());
        } else {
            throw new IOException("第" + rowNum + "行第3列数据错误");
        }
        Cell cell4 = row.getCell(3);
        if (cell4 != null && !cell4.getStringCellValue().equals("")) {
            work.setWorkname(cell4.getStringCellValue());
        } else {
            throw new IOException("第" + rowNum + "行第4列数据错误");
        }
        Cell cell5 = row.getCell(4);
        Cell cell6 = row.getCell(5);
        Cell cell7 = row.getCell(6);
        Cell cell8 = row.getCell(7);
        Cell cell9 = row.getCell(8);
        Cell cell10 = row.getCell(9);
        //当3个学生都不为空
        if ((cell5 != null && cell5.getStringCellValue().length()<=20) ||
                (cell6 != null && cell6.getStringCellValue().length()<=20) ||
                (cell7 != null && cell7.getStringCellValue().length()<=20)) {
            //将不为空的学生插入
            if (cell5 != null && cell5.getStringCellValue().length()<=20) {
                if(cell6 != null && cell6.getStringCellValue().length()==18){
                    work.setStudent1(cell5.getStringCellValue());
                    work.setStudent1Id(cell6.getStringCellValue());
                } else {
                    throw new IOException("第" + rowNum + "行第6列数据错误");
                }
            } else {
                throw new IOException("第" + rowNum + "行第5列数据错误");
            }

            if (cell7 != null && cell7.getStringCellValue().length()<=20) {
                if(cell8 != null && cell8.getStringCellValue().length()==18){
                    work.setStudent2(cell7.getStringCellValue());
                    work.setStudent2Id(cell8.getStringCellValue());
                } else {
                    throw new IOException("第" + rowNum + "行第8列数据错误");
                }
            } else {
                throw new IOException("第" + rowNum + "行第7列数据错误");
            }

            if (cell9 != null && cell9.getStringCellValue().length()<=20) {
                if(cell10 != null && cell10.getStringCellValue().length()==18){
                    work.setStudent3(cell9.getStringCellValue());
                    work.setStudent3Id(cell10.getStringCellValue());
                } else {
                    throw new IOException("第" + rowNum + "行第10列数据错误");
                }
                work.setStudent3(cell9.getStringCellValue());
            } else {
                throw new IOException("第" + rowNum + "行第9列数据错误");
            }
        } else {
            throw new IOException("学生不能全为空");
        }

        Cell cell11 = row.getCell(10);
        Cell cell12 = row.getCell(11);
        if (cell11 != null && cell11.getStringCellValue().length()<=20) {
            if (cell12 != null && cell12.getStringCellValue().length()==18) {
                work.setTeacher1(cell11.getStringCellValue());
                work.setTeacher1Id(cell12.getStringCellValue());
            } else {
                throw new IOException("第" + rowNum + "行第12列数据错误");
            }
        } else {
            throw new IOException("第" + rowNum + "行第11列数据错误");
        }
        Cell cell13 = row.getCell(12);
        Cell cell14 = row.getCell(13);
        if (cell13 != null && cell13.getStringCellValue().length()<=20) {
            if (cell14 != null && cell14.getStringCellValue().length()==18) {
                work.setTeacher2(cell13.getStringCellValue());
                work.setStudent2Id(cell14.getStringCellValue());
            } else {
                throw new IOException("第" + rowNum + "行第14列数据错误");
            }
        } else {
            throw new IOException("第" + rowNum + "行第13列数据错误");
        }
        return work;
    }
}
