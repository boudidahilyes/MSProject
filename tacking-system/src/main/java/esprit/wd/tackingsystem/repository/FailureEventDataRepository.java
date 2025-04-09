package esprit.wd.tackingsystem.repository;

import esprit.wd.tackingsystem.model.FailureEventData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FailureEventDataRepository extends MongoRepository<FailureEventData, String> {
}
