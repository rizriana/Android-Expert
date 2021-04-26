package id.pattern.mysimplecleanarchitecture.domain

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MessageInteractor(private val messageRepository: IMessageRepository): MessageUseCase {
    override fun getMessage(name: String): MessageEntity {
        return messageRepository.getWelcomeMessage(name)
    }
}