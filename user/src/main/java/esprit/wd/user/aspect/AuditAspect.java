package esprit.wd.user.aspect;


import esprit.wd.user.annotations.AuditEvent;
import esprit.wd.user.model.KafkaEventType;
import esprit.wd.user.model.User;
import esprit.wd.user.request.AuthenticationRequest;
import esprit.wd.user.request.RefreshTokenRequest;
import esprit.wd.user.request.RegisterRequest;
import esprit.wd.user.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
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
    private final JwtService jwtService;

    @Pointcut("@annotation(auditEvent)")
    public void auditPointcut(AuditEvent auditEvent) {

    }

    @AfterReturning(
            pointcut = "auditPointcut(auditEvent)",
            argNames = "joinPoint,auditEvent")
    public void auditSuccess(JoinPoint joinPoint, AuditEvent auditEvent) {
        System.out.println(Arrays.toString(joinPoint.getArgs()));
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
                .filter(arg -> arg instanceof AuthenticationRequest || arg instanceof RegisterRequest || arg instanceof RefreshTokenRequest || arg instanceof HttpServletRequest)
                .map(arg -> {
                    if (arg instanceof AuthenticationRequest) {
                        return ((AuthenticationRequest) arg).email();
                    } else if (arg instanceof RegisterRequest) {
                        return ((RegisterRequest) arg).email();
                    } else if (arg instanceof RefreshTokenRequest) {
                        return ((RefreshTokenRequest) arg).username();
                    } else {
                        return getMail(((HttpServletRequest) arg).getHeader("Authorization"));
                    }

                })
                .findFirst()
                .orElse(null);
    }


    private String getMail(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        String token = authHeader.substring(7);
        return jwtService.extractClaim(token, Claims::getSubject);
    }
}
