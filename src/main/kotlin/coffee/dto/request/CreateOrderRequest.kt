package coffee.dto.request

data class CreateOrderRequest(
    val drinkName: String,
    val totalPrice: Int,
    val telegramId: Long? = null
)