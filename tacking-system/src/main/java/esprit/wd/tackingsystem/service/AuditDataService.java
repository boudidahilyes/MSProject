package esprit.wd.tackingsystem.service;


import esprit.wd.tackingsystem.model.FailureEventData;
import esprit.wd.tackingsystem.model.SuccessEventData;
import esprit.wd.tackingsystem.repository.FailureEventDataRepository;
import esprit.wd.tackingsystem.repository.SuccessEventDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuditDataService {

    private final SuccessEventDataRepository successEventDataRepository;
    private final FailureEventDataRepository failureEventDataRepository;


    public List<SuccessEventData> getSuccessEvents() {
        return successEventDataRepository.findAll();
    }

    public List<SuccessEventData> getSuccessEventsByEventType(String eventType) {
        return successEventDataRepository.findByEventType(eventType);
    }

    public List<SuccessEventData> getSuccessEventsByEmail(String email) {
        return successEventDataRepository.findByEmail(email);
    }

    public List<FailureEventData> getFailureEvents() {
        return failureEventDataRepository.findAll();
    }
}
