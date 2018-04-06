package org.vatalu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vatalu.map.CertificateMapper;
import org.vatalu.model.entity.Certificate;
import org.vatalu.model.response.CertificateResponse;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.QueryResponse;
import org.vatalu.service.CertificateService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateMapper certificateMapper;


    @Override
    public QueryResponse<Certificate> findCertificates(Integer start, Integer size) {
        List<Certificate> certificates = certificateMapper.findCertificates(start * size, size);
        if (certificates.size() != 0) {
            return new QueryResponse<>(true, certificates.size(), certificates);
        }
        return new QueryResponse<>(false);
    }

    public CommonResponse updateById(long id, Boolean isjudged) {
        if (certificateMapper.updateById(id, String.valueOf(isjudged)) == 1) {
            return new CommonResponse(true);
        } else {
            return new CommonResponse(false);
        }
    }

    @Override
    public CertificateResponse insert(String number, String email, String address, String studentId, Timestamp date) {
        if (certificateMapper.insert(number, email, address, studentId, "false",date) == 1) {
            return new CertificateResponse(true, number, email, address, studentId, false);
        } else {
            return new CertificateResponse(false);
        }
    }
}
