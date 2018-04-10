package org.vatalu.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vatalu.service.impl.DownloadServiceImpl;
import org.vatalu.util.PasswordEncode;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@PropertySource(value = "classpath:file.properties", encoding = "utf-8")
public class DownloadController {

    @Autowired
    private DownloadServiceImpl downloadService;

    @Value("${filepath}")
    private String filepath;

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(String number) {
        String filename = filepath + PasswordEncode.md5Encode(number) + ".pdf";
        File file;
        if (downloadService.getCertificatePdf(number).getResult()) {
            file = new File(filename);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
