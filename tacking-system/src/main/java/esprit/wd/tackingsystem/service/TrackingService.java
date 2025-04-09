package esprit.wd.tackingsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import esprit.wd.tackingsystem.model.FailedEventData;
import esprit.wd.tackingsystem.model.UserEvent;
import esprit.wd.tackingsystem.repository.UserEventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class TrackingService {

    private final UserEventRepository userEventRepository;


    @KafkaListener(topics = "user_success_on_conn", groupId = "distributed_web")
    public void consumeMsg(Message<?> message) {
        System.out.println(message);
        var data = getJson((String) message.getPayload(), UserEvent.class);
        System.out.println(data);
        userEventRepository.save(data);
    }

    @KafkaListener(topics = "user_failed_on_conn", groupId = "distributed_web")
    public void consumeMsg2(Message<?> message) {
        var data = getJson((String) message.getPayload(), FailedEventData.class);
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
