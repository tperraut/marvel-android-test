package rocks.flawless.marveltestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splashscreen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rocks.flawless.marveltestapp.api.MainScope
import rocks.flawless.marveltestapp.helpers.start
import rocks.flawless.marveltestapp.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    private val _splashScreenDelayInMillis = if (BuildConfig.DEBUG) 1000L else 1500L
    private val _mainScope = MainScope(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        init()
    }

    private fun init() {
        activity_splash_screen_version_TextView.text = String.format(
            "%s:%s",
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE
        )
        _mainScope.launch {
            delay(_splashScreenDelayInMillis)
            start<HomeActivity>(true)
        }
    }
}