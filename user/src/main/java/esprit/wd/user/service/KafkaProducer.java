package esprit.wd.user.service;

import esprit.wd.user.dto.UserEventData;
import esprit.wd.user.model.KafkaEventType;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, UserEventData> kafkaTemplate;

    public void deliverSuccessMessage(String email, KafkaEventType eventType) {
        var userEvent = new UserEventData(eventType.name(), email, null);

        Message<UserEventData> message = MessageBuilder
                .withPayload(userEvent)
                .setHeader(KafkaHeaders.TOPIC, "user_success_on_conn")
                .build();

        kafkaTemplate.send(message);
    }

    public void deliverFailedMessage(String email, String error, KafkaEventType eventType) {

        var userFailedData = new UserEventData(eventType.name(), email, null);

        userFailedData.setMetadata(new HashMap<>() {{
            put("error", error);
        }});
        Message<UserEventData> message = MessageBuilder
                .withPayload(userFailedData)
                .setHeader(KafkaHeaders.TOPIC, "user_failed_on_conn")
                .build();

        kafkaTemplate.send(message);
    }


}