package coffee.dto.response

data class CreateOrderResponse(
    val success: Boolean,
    val orderId: Long? = null
)