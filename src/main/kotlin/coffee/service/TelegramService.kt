package coffee.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class TelegramService(
    @Value("\${telegram.bot-token}") private val botToken: String
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val restClient = RestClient.create()

    fun sendMessage(chatId: Long, text: String) {
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
            log.info("Сообщение успешно отправлено пользователю $chatId")
        } catch (e: Exception) {
            log.error("Не удалось отправить сообщение в TG ($chatId): ${e.message}")
        }
    }
}