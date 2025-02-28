package ioc.prototype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 도메인 중심 아키텍처
 */
public class ObjectPerspectiveService {

    @Autowired
    ObjectPerspectiveServiceRequestDao serviceRequestDao;

    @Autowired
    EmailService emailService;

    public void addNewServiceRequest(ObjectPerspectiveServiceRequest serviceRequest) {
        this.serviceRequestDao.add(serviceRequest);
        serviceRequest.notifyServiceRequestRegistration();
    }
}




class ObjectPerspectiveServiceRequest {

    Customer customer;
    String productNo;
    String description;
    String email;

    @Autowired
    EmailService emailService;

    public void notifyServiceRequestRegistration() {
        this.emailService.sendEmail(this.email);
    }
}

@Component
class ObjectPerspectiveServiceRequestDao {

    // ServiceRequest DAO object.

    public void add(ObjectPerspectiveServiceRequest serviceRequest) {

        // add ServiceRequst to the DB, and connect with customer

    }
}
