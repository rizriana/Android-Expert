package id.pattern.mysimplecleanarchitecture.di

import id.pattern.mysimplecleanarchitecture.data.IMessageDataSource
import id.pattern.mysimplecleanarchitecture.data.MessageDataSource
import id.pattern.mysimplecleanarchitecture.data.MessageRepository
import id.pattern.mysimplecleanarchitecture.domain.IMessageRepository
import id.pattern.mysimplecleanarchitecture.domain.MessageInteractor
import id.pattern.mysimplecleanarchitecture.domain.MessageUseCase

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object Injection {
    fun provideUseCase(): MessageUseCase {
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository(): IMessageRepository {
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource(): IMessageDataSource {
        return MessageDataSource()
    }
}