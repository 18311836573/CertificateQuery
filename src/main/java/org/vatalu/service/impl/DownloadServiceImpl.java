package org.vatalu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.vatalu.map.WorkMapper;
import org.vatalu.model.entity.Work;
import org.vatalu.util.PdfUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DownloadServiceImpl {
    @Autowired
    private WorkMapper workMapper;

    public boolean getCertificatePdf(String filepath,String pdfpath,String fontpath,String number) {
        Work work = workMapper.findByNumber(number);
        PdfUtil pdfHelper = new PdfUtil();
        List<Work> workList = new ArrayList<>();
        workList.add(work);
        System.out.println("调用生成pdf函数");
        pdfHelper.createPdfs(filepath, pdfpath, fontpath, workList);
        return  true;

    }
}
