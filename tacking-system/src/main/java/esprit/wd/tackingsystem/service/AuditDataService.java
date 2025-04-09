package esprit.wd.tackingsystem.service;


import esprit.wd.tackingsystem.model.FailureEventData;
import esprit.wd.tackingsystem.model.SuccessEventData;
import esprit.wd.tackingsystem.repository.FailureEventDataRepository;
import esprit.wd.tackingsystem.repository.SuccessEventDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditDataService {

    private final SuccessEventDataRepository successEventDataRepository;
    private final FailureEventDataRepository failureEventDataRepository;

    public AuditDataService(SuccessEventDataRepository successEventDataRepository, FailureEventDataRepository failureEventDataRepository) {
        this.successEventDataRepository = successEventDataRepository;
        this.failureEventDataRepository = failureEventDataRepository;
    }

    public List<SuccessEventData> getSuccessEvents() {
        return  successEventDataRepository.findAll();
    }

    public List<FailureEventData> getFailureEvents() {
        return  failureEventDataRepository.findAll();
    }
}
