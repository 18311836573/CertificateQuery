package org.vatalu.service;

import org.vatalu.model.response.CommonResponse;

public interface MailService {
    public CommonResponse sendMail(String filename,String name,String mailAccount);
}
