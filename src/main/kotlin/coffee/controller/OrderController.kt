package coffee.controller

import coffee.dto.request.CreateOrderRequest
import coffee.dto.response.CreateOrderResponse
import coffee.model.OrderEntity
import coffee.repository.OrderRepository
import coffee.service.TelegramService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class OrderController(
    private val orderRepository: OrderRepository,
    private val telegramService: TelegramService
) {

    @PostMapping("/order")
    fun createOrder(@RequestBody request: CreateOrderRequest): CreateOrderResponse {
        val order = orderRepository.save(
            OrderEntity(
                details = request.drinkName,
                totalPrice = request.totalPrice
            )
        )

        telegramService.sendOrderNotification(
            orderId = order.id!!,
            details = order.details,
            price = order.totalPrice
        )

        return CreateOrderResponse(success = true, orderId = order.id)
    }
}