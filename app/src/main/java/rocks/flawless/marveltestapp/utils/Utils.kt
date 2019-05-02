package rocks.flawless.marveltestapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.AnimRes

object Utils {

    fun startActivity(
        context: Context,
        intent: Intent,
        clearTask: Boolean = false,
        @AnimRes enterAnim: Int = android.R.anim.fade_in,
        @AnimRes exitAnim: Int = android.R.anim.fade_out
    ) {
        if (clearTask) {
            intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        (context as? Activity)?.overridePendingTransition(enterAnim, exitAnim)
    }
}