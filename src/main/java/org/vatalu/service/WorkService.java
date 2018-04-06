package org.vatalu.service;

import org.springframework.web.multipart.MultipartFile;
import org.vatalu.model.entity.Work;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.QueryResponse;
import org.vatalu.model.response.UploadFileResponse;
import org.vatalu.model.response.WorkResponse;

import java.util.List;

public interface WorkService {
    public QueryResponse<Work> findWork(String year, String workId, String workName, String school, String authorName);

    public QueryResponse<Work> findWorks(Integer start,Integer size);

    public CommonResponse updateByNumber(Work work);

    public WorkResponse insert(Work work);

    public CommonResponse deleteByNumber(int id);

    public UploadFileResponse upload(int year, int month, String area, MultipartFile file);

}
