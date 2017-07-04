package guru.springframework.controller

import guru.springframework.domain.Product
import guru.springframework.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/product")
open class ProductController @Autowired constructor(private val productService: ProductService) {

    @RequestMapping("/list")
    fun listProducts(model: Model): String {
        model.addAttribute("products", productService.listAll())
        return "product/list"
    }

    @RequestMapping("/view/{id}")
    fun getProduct(@PathVariable id: Int, model: Model): String {
        model.addAttribute("product", productService.getById(id))
        return "product/view"
    }

    @RequestMapping("/new")
    fun newProduct(model: Model): String {
        model.addAttribute("product", Product())
        return "product/form"
    }

    @RequestMapping("/edit/{id}")
    fun editProduct(@PathVariable id: Int, model: Model): String {
        model.addAttribute("product", productService.getById(id))
        return "product/form"
    }

    @RequestMapping("/delete/{id}")
    fun deleteProduct(@PathVariable id: Int): String {
        productService.delete(id)
        return "redirect:/product/list"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun saveProduct(product: Product): String {
        val savedProduct = productService.saveOrUpdate(product)
        return "redirect:/product/view/${savedProduct.id}"
    }
}
