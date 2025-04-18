package com.msproject.complaintresponse.service;
import com.msproject.complaintresponse.entity.ComplaintDto;
import com.msproject.complaintresponse.entity.ResponseComplaint;
import com.msproject.complaintresponse.exception.ComplaintNotFoundException;
import com.msproject.complaintresponse.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseService implements IResponseService {
    private  final ResponseRepository responseRepository;
    private  final ComplaintService complaintService;

    public ResponseComplaint addRespons(ResponseComplaint response, String responderId, String complaintId) {
       ComplaintDto complaintDTO = complaintService.getComplaintById(complaintId);
        if (complaintDTO == null) {
            throw new ComplaintNotFoundException("Complaint with id " + complaintId + " not found.");
        }
        complaintService.updateComplaintStatus(complaintDTO.getComplaintId(), "IN_PROGRESS");
        response.setResponderId(responderId);
        response.setComplaintId(complaintId);
        response.setCreatedAt(LocalDateTime.now());
        return responseRepository.save(response);
    }
    public void deleteResponse (ResponseComplaint response) {

        responseRepository.delete(response);
    }

    public List<ResponseComplaint> getResponsesByComplainId(String complaintId) {
        return  responseRepository.findAllByComplaintId(complaintId);
    }

    public ResponseComplaint  isSeen(String responseId) {
        ResponseComplaint responseComplaint=responseRepository.findById(responseId).get();
        if(responseComplaint!=null) {
            responseComplaint.setIsSeen(true);
            responseRepository.save(responseComplaint);
        }
       return responseComplaint;
    }

    public List<ResponseComplaint> getAllResponses() {

        return responseRepository.findAll();
    }

}
