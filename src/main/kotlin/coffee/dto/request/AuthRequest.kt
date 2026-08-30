package coffee.dto.request

data class AuthRequest(
    val telegramId: Long,
    val username: String?,
    val firstName: String?
)