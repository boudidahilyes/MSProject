package com.msproject.complaint.repository;
import com.msproject.complaint.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
