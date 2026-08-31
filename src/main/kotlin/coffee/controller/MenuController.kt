package coffee.controller

import coffee.dto.response.ProductResponse
import coffee.service.MenuService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = ["*"])
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping
    fun getMenu(): List<ProductResponse> {
        return menuService.getAvailableMenu()
    }
}