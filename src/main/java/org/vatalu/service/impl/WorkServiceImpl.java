package org.vatalu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.vatalu.map.WorkMapper;
import org.vatalu.model.dto.ExcelToWork;
import org.vatalu.model.entity.Work;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.QueryResponse;
import org.vatalu.model.response.UploadFileResponse;
import org.vatalu.model.response.WorkResponse;
import org.vatalu.service.WorkService;
import org.vatalu.util.AreaToCode;

import java.io.IOException;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkMapper workMapper;

    @Override
    public QueryResponse<Work> findWork(String year, String workId, String workName, String school, String authorName) {
        String[] studentArray = authorName.split(" ", 3);
        String student1 = null, student2 = null, student3 = null;
        student1 = studentArray[0];
        if (studentArray.length > 2) {
            student2 = studentArray[1];
            student3 = studentArray[2];
        } else if (studentArray.length == 2) {
            student2 = studentArray[1];
        }
        List<Work> works = workMapper.findWork(Integer.parseInt(year), workId, workName, school, student1, student2, student3);
        if (works.size() != 0) {
            return new QueryResponse<>(true, works.size(), works);
        } else {
            return new QueryResponse<>(false);
        }
    }

    @Override
    public QueryResponse<Work> findWorks(Integer start, Integer size) {
        List<Work> works = workMapper.findWorks(start * size, size);
        if (works.size() != 0) {
            return new QueryResponse<>(true, works.size(), works);
        } else {
            return new QueryResponse<>(false);
        }
    }

    @Override
    public CommonResponse updateByNumber(Work work) {
        if (1 == workMapper.updateByNumber(work)) {
            return new CommonResponse(true);
        } else {
            return new CommonResponse(false);
        }
    }

    @Override
    public WorkResponse insert(Work work) {
        if (1 == workMapper.insert(work)) {
            return new WorkResponse(true, work);
        } else {
            return new WorkResponse(false);
        }
    }

    @Override
    public CommonResponse deleteByNumber(int id) {
        if (1 == workMapper.deleteByNumber(id)) {
            return new CommonResponse(true);
        } else {
            return new CommonResponse(false);
        }
    }

    @Override
    public UploadFileResponse upload(int year, int month, String area, MultipartFile file) {
        try {
            List<Work> works = ExcelToWork.readExcel(file);
            String citycode = AreaToCode.getCode(area);
            if (citycode != null) {
                works.forEach((t) -> {
                    t.setYear(year);
                    t.setMonth(month);
                    t.setArea(area);
                    t.setNumber(year + month + citycode + t.getWorkid());
                });
                if (workMapper.insertWorks(works) == works.size()) {
                    return new UploadFileResponse(true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new UploadFileResponse(false, e.getMessage());
        }
        return new UploadFileResponse(false, "服务器错误");
    }
}
