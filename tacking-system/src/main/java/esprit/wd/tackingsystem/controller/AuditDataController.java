package esprit.wd.tackingsystem.controller;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import esprit.wd.tackingsystem.model.FailureEventData;
import esprit.wd.tackingsystem.model.SuccessEventData;
import esprit.wd.tackingsystem.service.AuditDataService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
@AllArgsConstructor
public class AuditDataController implements GraphQLQueryResolver {

    private final AuditDataService auditDataService;


    @QueryMapping
    public List<SuccessEventData> getSuccessEvents() {
        return auditDataService.getSuccessEvents();
    }

    @QueryMapping
    public List<FailureEventData> getFailureEvents() {
        return auditDataService.getFailureEvents();
    }
}
