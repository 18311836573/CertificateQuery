package org.vatalu.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MailController {
    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private DownloadServiceImpl downloadService;

    @PostMapping(value = "/mail", produces = "application/json;charset=utf-8")
    public CommonResponse sendMail(@RequestParam("number") String number,
                                   @RequestParam("mailAccount") String receiveMailAccount,
                                   HttpServletRequest request) {
        String filepath = request.getServletContext().getRealPath(File.separator + "download");
        String filename = filepath + File.separator + PasswordEncode.md5Encode(number) + ".pdf";
        File file = new File(filename);
        if (!file.exists()) {
            String pdfpath = request.getSession().getServletContext().getRealPath(File.separator + "pdfdemo");
            String fontpath = request.getSession().getServletContext().getRealPath(File.separator + "font");
            CommonResponse commonResponse = downloadService.getCertificatePdf(filepath, pdfpath, fontpath, number);
            if (!commonResponse.getResult()){
                return new CommonResponse(false);
            }
        }
        return mailService.sendMail(filename, number, receiveMailAccount);
    }
}
