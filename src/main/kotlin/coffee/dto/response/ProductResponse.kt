package coffee.dto.response

import coffee.model.ProductEntity
import java.math.BigDecimal

data class ProductResponse(
    val id: Long,
    val name: String,
    val category: String,
    val price: BigDecimal,
    val imageUrl: String?
) {
    companion object {
        fun fromEntity(entity: ProductEntity) = ProductResponse(
            id = entity.id!!,
            name = entity.name,
            category = entity.category,
            price = entity.price,
            imageUrl = entity.imageUrl
        )
    }
}