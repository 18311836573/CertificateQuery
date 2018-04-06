package org.vatalu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vatalu.model.entity.Certificate;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.QueryResponse;
import org.vatalu.service.CertificateService;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class CertificateController {

    private static final String SVIP = "svip";
    private static final String VIP = "vip";

    @Autowired
    private CertificateService certificateService;

    @GetMapping(value = "/certificates", produces = "application/json;charset=utf-8")
    public QueryResponse<Certificate> findCertificates(@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer size,
                                                       @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer page,
                                                       @SessionAttribute("level") String level) {
        if (level.equals(SVIP) || level.equals(VIP)) {
            if (page < 1) page = 1;
            if (size <= 0) size = 10;
            return certificateService.findCertificates(page - 1, size);
        } else {
            return new QueryResponse<>(false);
        }
    }


    @PutMapping(value = "/certificate", produces = "application/json;charset=utf-8")
    public CommonResponse updateCertificate(@RequestParam("id") long id,
                                            @RequestParam("status") Boolean status,
                                            @SessionAttribute("level") String level) {
        if (level.equals(SVIP) || level.equals(VIP)) {
            return certificateService.updateById(id, status);
        } else {
            return new CommonResponse(false);
        }
    }

    @PostMapping(value = "/certificate", produces = "application/json;charset=utf-8")
    public CommonResponse createCertificate(@RequestParam("number") String number,
                                            @RequestParam("email") String email,
                                            @RequestParam("address") String address,
                                            @RequestParam("studentId") String studentId,
                                            @RequestParam("date") Date date) {
        return certificateService.insert(number, email, address, studentId, new Timestamp(date.getTime()));
    }
}

