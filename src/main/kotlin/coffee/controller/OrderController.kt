package coffee.controller

import coffee.dto.request.CreateOrderRequest
import coffee.dto.response.CreateOrderResponse
import coffee.dto.response.LastOrderResponse
import coffee.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping("/order")
    fun createOrder(@RequestBody request: CreateOrderRequest): CreateOrderResponse {
        return orderService.createOrder(
            req = request,
            telegramId = request.telegramId
        )
    }

    @GetMapping("/order/last")
    fun getLastOrder(@RequestParam telegramId: Long): LastOrderResponse? {
        return orderService.getLastOrder(telegramId)
    }
}