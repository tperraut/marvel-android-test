package rocks.flawless.marveltestapp.hero

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hero_details.*
import rocks.flawless.marveltestapp.R
import rocks.flawless.marveltestapp.api.retrofit.models.Hero
import rocks.flawless.marveltestapp.api.retrofit.models.Thumbnail.Companion.SIZE_LARGE
import rocks.flawless.marveltestapp.helpers.start

class HeroDetailsActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_HERO = "EXTRA_HERO"

        fun start(context: Context, hero: Hero) {
            context.start<HeroDetailsActivity> {
                putExtra(EXTRA_HERO, hero)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_details)

        val hero: Hero = intent.getParcelableExtra(EXTRA_HERO)
        val description: String? = hero.description
        activity_hero_details_collapse_toolbar.title = hero.name
        if (TextUtils.isEmpty(description)) {
            activity_hero_details_text.setText(R.string.activity_hero_details_no_infos)
        } else {
            activity_hero_details_text.text = hero.description
        }
        activity_hero_details_img.setImageURI(hero.img?.getImgUrlBySize(SIZE_LARGE))
        activity_hero_details_toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}