package coffee.controller

import coffee.dto.request.AuthRequest
import coffee.dto.response.AuthResponse
import coffee.model.Role
import coffee.model.UserEntity
import coffee.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userRepository: UserRepository
) {

    @PostMapping("/me")
    fun getOrCreateUser(@RequestBody req: AuthRequest): AuthResponse {
        val user = userRepository.findByTelegramId(req.telegramId)
            ?: userRepository.save(
                UserEntity(
                    telegramId = req.telegramId,
                    username = req.username,
                    firstName = req.firstName,
                    role = Role.CLIENT
                )
            )

        return AuthResponse(
            userId = user.id!!,
            role = user.role.name
        )
    }
}