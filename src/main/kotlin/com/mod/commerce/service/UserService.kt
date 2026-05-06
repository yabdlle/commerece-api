package com.mod.commerce.service

import com.mod.commerce.domain.model.User
import com.mod.commerce.repository.UserRepository
import org.springframework.stereotype.Service

import java.util.UUID
import java.time.Instant
@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUserById(id: UUID): User {
        return userRepository.findById(id).orElseThrow { RuntimeException("User Not Found") }
    }
    fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw RuntimeException("User Not Found")
    }

    fun createUser(name: String, email: String, password: String): User {
        if(userRepository.existsByEmail(email)) {
            throw RuntimeException("User Already Exists")
        }
        val user = User(
            id = UUID.randomUUID(),
            email = email,
            name = name,
            password = password,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
        return userRepository.save(user)
    }

    fun updateUser(id: UUID, name: String, email: String): User {
        val user = getUserById(id)
        if (email != user.email && userRepository.existsByEmail(email))  {
            throw RuntimeException("User Already Exists")
        }
        val updatedUser = user.copy(
            name = name,
            email = email,
            updatedAt = Instant.now(),
        )
        return userRepository.save(updatedUser)
    }

    fun deleteUser(id: UUID){
        val user = getUserById(id)
        userRepository.delete(user)
    }
}

