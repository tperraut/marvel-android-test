package rocks.flawless.marveltestapp.home.adapter

import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView.NO_ID
import rocks.flawless.marveltestapp.api.retrofit.models.Hero
import rocks.flawless.marveltestapp.base.BaseAdapter
import rocks.flawless.marveltestapp.home.action.HomeAction
import rocks.flawless.marveltestapp.home.ui.HeroListItemView
import rocks.flawless.marveltestapp.home.vm.HomeViewModel

class HomeRecyclerAdapter(
    private val _vm: HomeViewModel,
    private val _listener: HeroListItemView.Listener,
    var isPagingEnabled: Boolean = true
) : BaseAdapter<Hero>() {

    companion object {
        const val TYPE_HERO: Int = 0
        const val TYPE_LOADER: Int = 1
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = when (viewType) {
            TYPE_HERO -> HeroListItemView(parent.context)
            else -> {
                val v = ProgressBar(parent.context)
                v.isIndeterminate = true
                v
            }
        }
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_HERO -> {
                val view: HeroListItemView = holder.itemView as HeroListItemView
                view.model = getItemAtPosition(position)
                view.listener = _listener
            }
            TYPE_LOADER -> _vm.handle(HomeAction.LoadMore)
        }
    }

    override fun getItemId(position: Int): Long {
        return if (isPagingEnabled && position == itemCount - 1) {
            Long.MAX_VALUE
        } else {
            getItemAtPosition(position).id?.toLong() ?: NO_ID
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPagingEnabled && position == itemCount - 1) {
            TYPE_LOADER
        } else {
            TYPE_HERO
        }
    }
}