package coffee.repository

import coffee.model.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {
    fun findAllByIsAvailableTrue(): List<ProductEntity>
}