package rocks.flawless.marveltestapp.api

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * This class create a scope on Main Thread
 * and embedded the current running job and can be connected to activity lifecycle
 *
 * If you want to manage the job flow yourself, just don't pass lifecycle to constructor and safeCall
 * onCreate and onCancel as you wish
 *
 * @property Lifecycle the activity lifecycle
 * @constructor Creates a scope on Main Thread and create a new job for first use
 */
class MainScope(lifecycle: Lifecycle? = null) : CoroutineScope, LifecycleObserver {

    private var _job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + _job

    init {
        lifecycle?.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (!_job.isActive) {
            _job = Job()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onCancel() {
        if (_job.isActive) {
            _job.cancel()
        }
    }
}