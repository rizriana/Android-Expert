package id.pattern.mysimplecleanarchitecture.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.pattern.mysimplecleanarchitecture.domain.MessageEntity
import id.pattern.mysimplecleanarchitecture.domain.MessageUseCase

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MainViewModel(private val messageUseCase: MessageUseCase) : ViewModel() {
    private val _message = MutableLiveData<MessageEntity>()
    val message: LiveData<MessageEntity>
        get() = _message

    fun setName(name: String) {
        _message.value = messageUseCase.getMessage(name)
    }
}