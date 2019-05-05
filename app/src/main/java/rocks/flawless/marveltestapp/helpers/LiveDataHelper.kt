package rocks.flawless.marveltestapp.helpers

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

fun <X, Y> LiveData<X>.map(f: (X) -> Y) = Transformations.map(this, f)

fun <T> LiveData<T>.bind(owner: LifecycleOwner, f: (T) -> Unit) = this.observe(owner, Observer(f))