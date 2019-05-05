package rocks.flawless.marveltestapp.home.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.list_item_view_hero.view.*
import rocks.flawless.marveltestapp.R
import rocks.flawless.marveltestapp.api.retrofit.models.Hero

class HeroListItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var _model: Hero? = null
    var model: Hero
        get() = _model!!
        set(value) {
            _model = value
            updateUi(value)
        }
    var listener: Listener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.list_item_view_hero, this, true)

        elevation = resources.getDimension(R.dimen.card_elevation_default)
        radius = resources.getDimension(R.dimen.card_radius_medium)
        setOnClickListener { listener?.onHeroClicked(model) }
    }

    private fun updateUi(hero: Hero) {
        list_item_view_hero_text.text = hero.name
        list_item_view_hero_img.setImageURI(hero.img?.getImgUrlBySize())
    }

    interface Listener {
        fun onHeroClicked(hero: Hero)
    }
}