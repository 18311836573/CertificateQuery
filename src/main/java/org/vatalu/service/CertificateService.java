package org.vatalu.service;

import org.vatalu.model.entity.Certificate;
import org.vatalu.model.response.CertificateResponse;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.QueryResponse;

import java.sql.Timestamp;

public interface CertificateService {
    public QueryResponse<Certificate> findCertificates(Integer start,Integer size);

    public CommonResponse updateById(long id, Boolean isjudged);

    public CertificateResponse insert(String number, String email, String address, String studentId, Timestamp date);

}
