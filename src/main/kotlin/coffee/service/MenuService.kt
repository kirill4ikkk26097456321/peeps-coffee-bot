package coffee.service

import coffee.dto.response.ProductResponse
import coffee.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MenuService(
    private val productRepository: ProductRepository
) {

    @Transactional(readOnly = true)
    fun getAvailableMenu(): List<ProductResponse> {
        return productRepository.findAllByIsAvailableTrue()
            .map { ProductResponse.fromEntity(it) }
    }
}