package guru.springframework.service.stub

import guru.springframework.domain.Product
import guru.springframework.service.ProductService
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("stub")
class ProductServiceStubImpl : AbstractServiceStub<Product>(), ProductService
