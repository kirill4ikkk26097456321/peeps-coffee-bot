package coffee.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class TelegramService(
    @Value("\${telegram.bot-token}") private val botToken: String,
    @Value("\${telegram.chat-id}") private val chatId: String
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val restClient = RestClient.create()

    fun sendOrderNotification(orderId: Long, details: String, price: Int) {
        val text = """
            ⚡️ <b>ЗАКАЗ #$orderId</b>
            ☕️ <b>Напиток:</b> $details
            💰 <b>К оплате:</b> $price руб.
        """.trimIndent()

        try {
            restClient.post()
                .uri("https://api.telegram.org/bot$botToken/sendMessage")
                .body(
                    mapOf(
                        "chat_id" to chatId,
                        "text" to text,
                        "parse_mode" to "HTML"
                    )
                )
                .retrieve()
                .toBodilessEntity()

            log.info("Уведомление по заказу #$orderId успешно отправлено в чат бариста")
        } catch (e: Exception) {
            log.error("Ошибка отправки сообщения в Telegram: ${e.message}", e)
        }
    }
}