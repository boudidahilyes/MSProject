package esprit.wd.tackingsystem.repository;

import esprit.wd.tackingsystem.model.SuccessEventData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SuccessEventDataRepository extends MongoRepository<SuccessEventData, String> {
}
