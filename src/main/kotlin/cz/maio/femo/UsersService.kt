package cz.maio.femo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class UsersService(
    private val objectMapper: ObjectMapper
) {
    private val usersJson = ClassPathResource("/data/users.json")

    data class User(
        val name: String,
        val email: String
    )

    fun getUsers(): List<User> = objectMapper.readValue<List<User>>(usersJson.inputStream)

    fun getUsersAsJsonNode() = objectMapper.readTree(usersJson.inputStream)
}