package coffee.controller

import coffee.dto.response.AdminOrderResponse
import coffee.dto.request.UpdateStatusRequest
import coffee.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin/orders")
class AdminOrderController(
    private val orderService: OrderService
) {

    @GetMapping
    fun getActiveOrders(): List<AdminOrderResponse> {
        return orderService.getActiveOrders()
    }

    @PatchMapping("/{orderId}/status")
    fun updateOrderStatus(
        @PathVariable orderId: Long,
        @RequestBody req: UpdateStatusRequest
    ): AdminOrderResponse {
        return orderService.updateStatus(orderId, req)
    }
}