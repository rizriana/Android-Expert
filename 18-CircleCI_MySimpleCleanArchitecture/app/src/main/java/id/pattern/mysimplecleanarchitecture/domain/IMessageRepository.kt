package id.pattern.mysimplecleanarchitecture.domain

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface IMessageRepository {
    fun getWelcomeMessage(name: String): MessageEntity
}