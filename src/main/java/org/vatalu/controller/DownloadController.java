package org.vatalu.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.service.impl.DownloadServiceImpl;
import org.vatalu.util.PasswordEncode;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class DownloadController {

    @Autowired
    private DownloadServiceImpl downloadService;

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, String number) {
        System.out.println();
        String filepath = request.getSession().getServletContext().getRealPath(File.separator + "download");
        String pdfpath = request.getSession().getServletContext().getRealPath(File.separator + "pdfdemo");
        String fontpath = request.getSession().getServletContext().getRealPath(File.separator + "font");
        String filename = request.getServletContext().getRealPath(File.separator + "download") + File.separator + PasswordEncode.md5Encode(number)
                + ".pdf";
        File file = new File(filename);
        if (!file.exists()) {
            if (downloadService.getCertificatePdf(filepath, pdfpath, fontpath, number).getResult()) {
                file = new File(filename);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
