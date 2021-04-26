package id.pattern.mysimplecleanarchitecture.data

import id.pattern.mysimplecleanarchitecture.domain.MessageEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MessageDataSource : IMessageDataSource {
    override fun getMessageFromSource(name: String): MessageEntity {
        return MessageEntity("Hello $name! Welcome to Clean Architecture")
    }
}