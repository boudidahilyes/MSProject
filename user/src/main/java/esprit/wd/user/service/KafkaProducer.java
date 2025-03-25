package esprit.wd.user.service;

import esprit.wd.user.dto.FailedEventData;
import esprit.wd.user.dto.UserEvent;
import esprit.wd.user.model.KafkaEventType;
import esprit.wd.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void deliverSuccessMessage(User user, KafkaEventType eventType) {
        var userEvent = new UserEvent(eventType.name(), user.getUserId(), null);

        Message<UserEvent> message = MessageBuilder
                .withPayload(userEvent)
                .setHeader(KafkaHeaders.TOPIC, "user_success_on_conn")
                .build();

        System.out.println("message: " + message);

        kafkaTemplate.send(message);
    }

    public void deliverFailedMessage(FailedEventData data) {

        Message<FailedEventData> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "user_failed_on_conn")
                .build();

        System.out.println("message: " + message);

        kafkaTemplate.send(message);
    }


}