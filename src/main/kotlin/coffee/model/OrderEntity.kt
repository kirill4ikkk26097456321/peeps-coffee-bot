package coffee.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val details: String,

    @Column(name = "total_price", nullable = false)
    val totalPrice: Int,

    @Column(nullable = false)
    var status: String = "НОВЫЙ",

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)