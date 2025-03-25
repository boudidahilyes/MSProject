package esprit.wd.tackingsystem.repository;

import esprit.wd.tackingsystem.model.UserEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEventRepository extends MongoRepository<UserEvent, String> {
}
