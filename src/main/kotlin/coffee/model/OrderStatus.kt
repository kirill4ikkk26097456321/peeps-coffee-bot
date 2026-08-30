package coffee.model

enum class OrderStatus(val title: String) {
    CREATED("Принят"),
    COOKING("Готовится"),
    READY("Готов к выдаче"),
    COMPLETED("Выдан"),
    CANCELLED("Отменен")
}