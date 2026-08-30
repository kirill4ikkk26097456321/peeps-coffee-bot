package coffee.dto.request

import coffee.model.OrderStatus

data class UpdateStatusRequest(
    val status: OrderStatus
)