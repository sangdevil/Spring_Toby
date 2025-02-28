package ioc.prototype;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 데이터 중심 아키텍처
 */
public class DataPerspectiveService {




    @Autowired
    CustomerDao customerDao;

    @Autowired
    ServiceRequestDao serviceRequestDao;

    @Autowired
    EmailService emailService;

    public void ServiceRequestFromSubmit(HttpServletRequest request) {
        // from the request form, get necessary information,
        // and make the Objects and insert it in DB.
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setCustomerNo(request.getParameter("customerNo"));

        // ...
        this.addNewServiceRequest(serviceRequest);

    }

    public void addNewServiceRequest(ServiceRequest serviceRequest) {
        Customer customer = this.customerDao.findCustomerByNo(
                serviceRequest.getCustomerNo());
        // ...
        this.serviceRequestDao.add(serviceRequest, customer);

        // ...

        this.emailService.sendEmail(customer.getEmail());
    }


}

@Component
class EmailService {

    // Email service

    public void sendEmail(String email) {

        // sendEmail.
    }
}

@Component
class ServiceRequestDao {

    // ServiceRequest DAO object.

    public void add(ServiceRequest serviceRequest, Customer customer) {

        // add ServiceRequst to the DB, and connect with customer

    }
}

@Component
class CustomerDao {

    // Customer DAO object.

    public Customer findCustomerByNo(String No) {

        // Actually, find someone who has no "No"
        return new Customer();
    }

}

@Getter
@Setter
@RequiredArgsConstructor
class Customer {

    String customerNo;
    String firstName;
    String email;
    // ...

}

@Getter
@Setter
@RequiredArgsConstructor
class ServiceRequest {
    String customerNo;
    String productNo;
    String description;
    // ... etc
}

