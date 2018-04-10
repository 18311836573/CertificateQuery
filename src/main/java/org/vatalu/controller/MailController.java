package org.vatalu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.service.impl.DownloadServiceImpl;
import org.vatalu.service.impl.MailServiceImpl;
import org.vatalu.util.PasswordEncode;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@PropertySource(value = "classpath:file.properties", encoding = "utf-8")
public class MailController {
    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private DownloadServiceImpl downloadService;

    @Value("${filepath}")
    private String filepath;

    @PostMapping(value = "/mail", produces = "application/json;charset=utf-8")
    public CommonResponse sendMail(@RequestParam("number") String number,
                                   @RequestParam("mailAccount") String receiveMailAccount) {
        String filename = filepath + PasswordEncode.md5Encode(number) + ".pdf";
        File file = new File(filename);
        if (!file.exists()) {
            CommonResponse commonResponse = downloadService.getCertificatePdf( number);
            if (!commonResponse.getResult()){
                return new CommonResponse(false);
            }
        }
        return mailService.sendMail(filename, number, receiveMailAccount);
    }
}
