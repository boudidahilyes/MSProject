package esprit.wd.user.aspect;


import esprit.wd.user.annotations.AuditEvent;
import esprit.wd.user.model.KafkaEventType;
import esprit.wd.user.model.User;
import esprit.wd.user.request.AuthenticationRequest;
import esprit.wd.user.request.RefreshTokenRequest;
import esprit.wd.user.request.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import esprit.wd.user.service.KafkaProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor
public class AuditAspect {

    private final KafkaProducer kafkaProducer;

    @Pointcut("@annotation(auditEvent)")
    public void auditPointcut(AuditEvent auditEvent) {

    }

    @AfterReturning(
            pointcut = "auditPointcut(auditEvent)",
            argNames = "joinPoint,auditEvent")
    public void auditSuccess(JoinPoint joinPoint, AuditEvent auditEvent) {
        KafkaEventType eventType = auditEvent.eventType();
        kafkaProducer.deliverSuccessMessage(extractEmailFromArgs(joinPoint.getArgs()), eventType);
    }

    @AfterThrowing(
            pointcut = "auditPointcut(auditEvent)",
            throwing = "ex",
            argNames = "joinPoint,auditEvent,ex")
    public void auditFailure(JoinPoint joinPoint, AuditEvent auditEvent, Exception ex) {
        KafkaEventType eventType = auditEvent.eventType();
        kafkaProducer.deliverFailedMessage(extractEmailFromArgs(joinPoint.getArgs()), ex.getMessage(), eventType);
    }

    private String extractEmailFromArgs(Object[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg instanceof AuthenticationRequest || arg instanceof RegisterRequest || arg instanceof RefreshTokenRequest)
                .map(arg -> {
                    if (arg instanceof AuthenticationRequest) {
                        return ((AuthenticationRequest) arg).email();
                    } else if (arg instanceof RegisterRequest) {
                        return ((RegisterRequest) arg).email();
                    } else {
                        return ((RefreshTokenRequest) arg).username();
                    }

                })
                .findFirst()
                .orElse(null);
    }
}
