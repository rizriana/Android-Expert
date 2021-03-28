package id.pattern.mysimplecleanarchitecture.data

import id.pattern.mysimplecleanarchitecture.domain.MessageEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface IMessageDataSource {
    fun getMessageFromSource(name: String): MessageEntity
}