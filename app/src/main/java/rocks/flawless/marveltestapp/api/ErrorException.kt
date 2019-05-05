package rocks.flawless.marveltestapp.api

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.annotation.StringRes

class ErrorException(
    @StringRes val errMsg: Int,
    cause: Throwable? = null
) : Throwable(cause = cause) {

    fun showToast(context: Context) {
        Toast.makeText(context, errMsg, LENGTH_LONG).show()
    }
}