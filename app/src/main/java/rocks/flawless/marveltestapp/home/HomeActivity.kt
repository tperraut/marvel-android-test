package rocks.flawless.marveltestapp.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rocks.flawless.marveltestapp.R
import rocks.flawless.marveltestapp.utils.Utils

class HomeActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            Utils.startActivity(context, Intent(context, HomeActivity::class.java), true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
