package id.pattern.mysimplecleanarchitecture.domain

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface MessageUseCase {
    fun getMessage(name: String): MessageEntity
}