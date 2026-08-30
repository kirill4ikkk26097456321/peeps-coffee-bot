package coffee.dto.response

import coffee.model.OrderStatus
import java.time.LocalDateTime

data class AdminOrderResponse(
    val id: Long,
    val customerName: String,
    val details: String,
    val totalPrice: Int,
    val status: OrderStatus,
    val createdAt: LocalDateTime
)