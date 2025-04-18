package esprit.wd.tackingsystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.shared.sharedlibrary.model.UserEvent;
import esprit.wd.tackingsystem.model.FailureEventData;
import esprit.wd.tackingsystem.model.SuccessEventData;
import esprit.wd.tackingsystem.repository.FailureEventDataRepository;
import esprit.wd.tackingsystem.repository.SuccessEventDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class TrackingService {

    private final SuccessEventDataRepository successEventDataRepository;
    private final FailureEventDataRepository failureEventDataRepository;


    @KafkaListener(topics = "user_success_on_conn", groupId = "distributed_web")
    public void consumeMsgOnSuccess(Message<?> message) {
        var data = getJson((String) message.getPayload(), SuccessEventData.class);
        System.out.println(data);
        assert data != null;
        successEventDataRepository.save(data);
    }

    @KafkaListener(topics = "user_failed_on_conn", groupId = "distributed_web")
    public void consumeMsgOnFailure(Message<?> message) {
        var data = getJson((String) message.getPayload(), FailureEventData.class);
        System.out.println(data);
        assert data != null;
        failureEventDataRepository.save(data);
    }

    private <T> T getJson(String payload, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(payload, tClass);
        } catch (Exception e) {
            log.error("Failed to parse JSON: {}", payload);
        }
        return null;
    }

}
