package id.pattern.mysimplecleanarchitecture.data

import id.pattern.mysimplecleanarchitecture.domain.IMessageRepository
import id.pattern.mysimplecleanarchitecture.domain.MessageEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MessageRepository(private val messageDataSource: IMessageDataSource) :IMessageRepository {
    override fun getWelcomeMessage(name: String): MessageEntity {
        return messageDataSource.getMessageFromSource(name)
    }

}