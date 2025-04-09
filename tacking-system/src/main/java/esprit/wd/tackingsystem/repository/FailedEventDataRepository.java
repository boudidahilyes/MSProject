package esprit.wd.tackingsystem.repository;

import esprit.wd.tackingsystem.model.FailedEventData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FailedEventDataRepository extends MongoRepository<FailedEventData, String> {
}
