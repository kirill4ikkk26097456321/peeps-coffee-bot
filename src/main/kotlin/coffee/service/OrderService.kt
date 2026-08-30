package coffee.service

import coffee.dto.response.AdminOrderResponse
import coffee.dto.request.CreateOrderRequest
import coffee.dto.response.CreateOrderResponse
import coffee.dto.request.UpdateStatusRequest
import coffee.model.OrderEntity
import coffee.model.OrderStatus
import coffee.repository.OrderRepository
import coffee.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val telegramService: TelegramService
) {

    @Transactional
    fun createOrder(req: CreateOrderRequest, telegramId: Long?): CreateOrderResponse {
        val user = telegramId?.let { userRepository.findByTelegramId(it) }

        val order = orderRepository.save(
            OrderEntity(
                user = user,
                details = req.drinkName,
                totalPrice = req.totalPrice,
                status = OrderStatus.CREATED
            )
        )
        return CreateOrderResponse(success = true, orderId = order.id)
    }

    fun getActiveOrders(): List<AdminOrderResponse> {
        val activeStatuses = listOf(OrderStatus.COMPLETED, OrderStatus.CANCELLED)
        return orderRepository.findAllByStatusNotInOrderByCreatedAtDesc(activeStatuses).map { order ->
            AdminOrderResponse(
                id = order.id!!,
                customerName = order.user?.firstName ?: "Гость",
                details = order.details,
                totalPrice = order.totalPrice,
                status = order.status,
                createdAt = order.createdAt
            )
        }
    }


    @Transactional
    fun updateStatus(orderId: Long, req: UpdateStatusRequest): AdminOrderResponse {
        val order = orderRepository.findById(orderId)
            .orElseThrow { IllegalArgumentException("Заказ #$orderId не найден") }

        order.status = req.status
        order.updatedAt = LocalDateTime.now()
        val updated = orderRepository.save(order)

        if (req.status == OrderStatus.READY && order.user != null) {
            val text = """
                ☕️ <b>Ваш заказ #${order.id} готов!</b>
                Напиток: <i>${order.details}</i>
                
                Пожалуйста, заберите его на стойке выдачи. Приятного кофепития! ✨
            """.trimIndent()
            telegramService.sendMessage(order.user!!.telegramId, text)
        }

        return AdminOrderResponse(
            id = updated.id!!,
            customerName = updated.user?.firstName ?: "Гость",
            details = updated.details,
            totalPrice = updated.totalPrice,
            status = updated.status,
            createdAt = updated.createdAt
        )
    }
}