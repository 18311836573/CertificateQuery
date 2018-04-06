package org.vatalu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.service.impl.MailServiceImpl;
import org.vatalu.util.PasswordEncode;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
public class MailController {
    @Autowired
    private MailServiceImpl mailService;

    @PostMapping(value = "/mail",produces = "application/json;charset=utf-8")
    public CommonResponse sendMail(@RequestParam("number") String number,@RequestParam("mailAccount") String receiveMailAccount, HttpServletRequest request) {
        String filepath = request.getServletContext().getRealPath("/download");
        String filename = filepath + File.separator + PasswordEncode.md5Encode(number) + ".pdf";
        return mailService.sendMail(filename,number,receiveMailAccount);
    }
}
