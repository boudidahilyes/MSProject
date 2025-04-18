package com.msproject.complaintresponse.service;

import com.msproject.complaintresponse.entity.ResponseComplaint;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
public interface IResponseService {
    ResponseComplaint addRespons(ResponseComplaint response, String responderId, String complaintId);

    void deleteResponse(ResponseComplaint response);

    List<ResponseComplaint> getResponsesByComplainId(String complaintId);

    ResponseComplaint isSeen(String responseId);

    List<ResponseComplaint> getAllResponses();
}
