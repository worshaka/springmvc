package guru.springframework.controller

import guru.springframework.domain.Customer
import guru.springframework.service.CustomerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/customer")
class CustomerController constructor(private val customerService: CustomerService) {

    @RequestMapping("/list", "/", "")
    fun listCustomers(model: Model): String {
        model.addAttribute("users", customerService.listAll())
        return "customer/list"
    }

    @RequestMapping("/view/{id}")
    fun showCustomer(@PathVariable id: Int, model: Model): String {
        model.addAttribute("customer", customerService.getById(id))
        return "customer/view"
    }

    @RequestMapping("/new")
    fun createCustomer(model: Model): String {
        model.addAttribute("customer", Customer())
        return "customer/form"
    }

    @RequestMapping("/edit/{id}")
    fun editCustomer(@PathVariable id: Int, model: Model): String {
        model.addAttribute("customer", customerService.getById(id))
        return "customer/form"
    }

    @RequestMapping("/delete/{id}")
    fun deleteCustomer(@PathVariable id: Int): String {
        customerService.delete(id)
        return "redirect:/customer/list"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun saveCustomer(customer: Customer): String {
        val savedCustomer = customerService.saveOrUpdate(customer)
        return "redirect:/customer/view/${savedCustomer.id}"
    }
}
