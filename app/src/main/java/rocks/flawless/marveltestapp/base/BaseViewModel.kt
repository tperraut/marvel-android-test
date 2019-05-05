package rocks.flawless.marveltestapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State, A : Action>(
    private val initialState: S
) : ViewModel() {
    private val _state = MutableLiveData<S>().apply { value = initialState }
    val state: LiveData<S>
        get() = _state
    open var job: Job? = null

    abstract fun handle(action: A)

    fun updateState(handler: (S) -> S) {
        val currentState = _state.value ?: initialState
        _state.value = handler(currentState)
    }

    fun launchVm(cancel: Boolean = true, f: suspend () -> Unit) {
        if (cancel && job?.isActive == true) {
            job!!.cancel()
        }
        job = viewModelScope.launch {
            f()
        }
    }
}