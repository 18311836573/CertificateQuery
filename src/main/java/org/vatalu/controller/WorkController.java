package org.vatalu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vatalu.model.entity.Work;
import org.vatalu.model.response.CommonResponse;
import org.vatalu.model.response.QueryResponse;
import org.vatalu.model.response.UploadFileResponse;
import org.vatalu.model.response.WorkResponse;
import org.vatalu.service.impl.WorkServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

@RestController
public class WorkController {
    private static final String SVIP = "svip";
    private static final String VIP = "vip";
    @Autowired
    private WorkServiceImpl workService;

    @GetMapping(value = "/work", produces = "application/json;charset=utf-8")
    public QueryResponse<Work> findWork(@RequestParam(value = "year", defaultValue = "\"\"", required = false) String year,
                                        @RequestParam(value = "workId", defaultValue = "\"\"", required = false) String workId,
                                        @RequestParam(value = "workName", defaultValue = "\"\"") String workName,
                                        @RequestParam(value = "school", defaultValue = "\"\"", required = false) String school,
                                        @RequestParam(value = "authorName", defaultValue = "\"\"", required = false) String authorName) {
        return workService.findWork(year, workId, workName, school, authorName);
    }

    @GetMapping(value = "/works", produces = "application/json;charset=utf-8")
    public QueryResponse<Work> findWorks(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer page,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer size) {
        if (page < 1) page = 1;
        if (size <= 0) size = 10;
        return workService.findWorks(page - 1, size);
    }

    @PostMapping(value = "/work", produces = "application/json;charset=utf-8")
    public WorkResponse insert(Work work, @SessionAttribute("level") String level) {
        if (level.equals(SVIP)||level.equals(VIP)) {
            return workService.insert(work);
        } else {
            return new WorkResponse(false);
        }
    }

    @PutMapping(value = "/work", produces = "application/json;charset=utf-8")
    public CommonResponse update(Work work, @SessionAttribute("level") String level) {
        if (level.equals(VIP)||level.equals(SVIP)) {
            return workService.updateByNumber(work);
        } else {
            return new CommonResponse(false);
        }
    }

    @DeleteMapping(value = "/work", produces = "application/json;charset=utf-8")
    public CommonResponse delete(@RequestParam("id") int id,@SessionAttribute("level") String level) {
        if (level.equals(VIP)||level.equals(SVIP)) {
            return workService.deleteByNumber(id);
        } else {
            return new CommonResponse(false);
        }
    }

    @PutMapping(value = "/work/upload", produces = "application/json;charset=utf-8")
    public UploadFileResponse upload(@RequestParam("year") Integer year,
                                     @RequestParam("month") Integer month,
                                     @RequestParam("area") String area,
                                     @RequestParam("excel") MultipartFile file,
                                     @SessionAttribute("level") String level) {
        if(level.equals(SVIP)||level.equals(VIP)){
            return workService.upload(year,month,area,file);
        } else {
            return new UploadFileResponse(false);
        }
    }



}
