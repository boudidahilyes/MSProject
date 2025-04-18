package com.msproject.complaintresponse.repository;
import com.msproject.complaintresponse.entity.ResponseComplaint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResponseRepository extends MongoRepository<ResponseComplaint,String> {
    List<ResponseComplaint>  findAllByComplaintId(String complaintId);

}
