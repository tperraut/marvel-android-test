package rocks.flawless.marveltestapp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class MarvelAndroidTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Fresco
        Fresco.initialize(this)
    }
}