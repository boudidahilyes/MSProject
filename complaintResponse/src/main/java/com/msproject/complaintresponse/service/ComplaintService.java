package com.msproject.complaintresponse.service;

import com.msproject.complaintresponse.entity.ComplaintDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "complaint")
public interface ComplaintService {
    @GetMapping("/api/v1/complaint/get/{id}")
    ComplaintDto getComplaintById(@PathVariable("id") String id);

    @PutMapping("/api/v1/complaint/{idComplaint}/{status}")
    void updateComplaintStatus(@PathVariable("idComplaint") String idComplaint, @PathVariable("status") String status);
}
