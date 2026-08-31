package coffee.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "products")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var category: String,

    @Column(nullable = false)
    var price: BigDecimal,

    @Column(name = "image_url")
    var imageUrl: String? = null,

    @Column(name = "is_available", nullable = false)
    var isAvailable: Boolean = true
)