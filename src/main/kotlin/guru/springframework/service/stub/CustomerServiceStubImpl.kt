package guru.springframework.service.stub

import guru.springframework.domain.Customer
import guru.springframework.service.CustomerService
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("stub")
class CustomerServiceStubImpl : AbstractServiceStub<Customer>(), CustomerService
