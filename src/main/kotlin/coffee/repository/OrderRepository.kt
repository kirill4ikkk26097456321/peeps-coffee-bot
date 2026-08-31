package coffee.repository

import coffee.model.OrderEntity
import coffee.model.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<OrderEntity, Long> {
    fun findAllByStatusNotInOrderByCreatedAtDesc(statuses: List<OrderStatus>): List<OrderEntity>
    fun findFirstByTelegramIdOrderByIdDesc(telegramId: Long): OrderEntity?
}