package esprit.wd.user.repository;


import esprit.wd.user.model.TokenBlackList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface TokenBlackListRepository extends MongoRepository<TokenBlackList, String> {

    Optional<TokenBlackList> findByJti(String jti);
}
