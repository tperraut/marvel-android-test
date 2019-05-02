package rocks.flawless.marveltestapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S : State, A : Action>(
    private val initialState: S
) : ViewModel() {
    private val _state = MutableLiveData<S>().apply { value = initialState }
    val state: LiveData<S>
        get() = _state

    abstract fun handle(action: A)

    fun updateState(handler: (S) -> S) {
        val currentState = _state.value ?: initialState
        _state.value = handler(currentState)
    }
}