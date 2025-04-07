package com.msproject.complaint.repository;
import com.msproject.complaint.entity.ResponseComplaint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResponseRepository extends MongoRepository<ResponseComplaint,String> {
    List<ResponseComplaint>  findAllByComplaintId(String complaintId);

}
