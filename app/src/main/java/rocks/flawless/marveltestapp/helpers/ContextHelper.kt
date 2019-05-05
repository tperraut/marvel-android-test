package rocks.flawless.marveltestapp.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.AnimRes

inline fun <reified T> Context.start(
    clearTask: Boolean = false,
    @AnimRes enterAnim: Int = android.R.anim.fade_in,
    @AnimRes exitAnim: Int = android.R.anim.fade_out,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java)
    intent.init()
    if (clearTask) {
        intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
    (this as? Activity)?.overridePendingTransition(enterAnim, exitAnim)
}