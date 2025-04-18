package esprit.wd.tackingsystem.repository;

import esprit.wd.tackingsystem.model.SuccessEventData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SuccessEventDataRepository extends MongoRepository<SuccessEventData, String> {
    List<SuccessEventData> findByEventType(String eventType);

    List<SuccessEventData> findByEmail(String email);
}
