package rocks.flawless.marveltestapp.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.geronimostudios.coffeescene.SceneCreator
import com.geronimostudios.coffeescene.SceneManager
import com.geronimostudios.coffeescene.annotations.Scene
import kotlinx.android.synthetic.main.activity_home.*
import rocks.flawless.marveltestapp.R
import rocks.flawless.marveltestapp.api.ErrorException
import rocks.flawless.marveltestapp.api.retrofit.models.Data
import rocks.flawless.marveltestapp.api.retrofit.models.Hero
import rocks.flawless.marveltestapp.helpers.RecyclerMarginDecorator
import rocks.flawless.marveltestapp.helpers.bind
import rocks.flawless.marveltestapp.helpers.map
import rocks.flawless.marveltestapp.hero.HeroDetailsActivity
import rocks.flawless.marveltestapp.home.action.HomeAction
import rocks.flawless.marveltestapp.home.adapter.HomeRecyclerAdapter
import rocks.flawless.marveltestapp.home.ui.HeroListItemView
import rocks.flawless.marveltestapp.home.vm.HomeViewModel

class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener, HeroListItemView.Listener {

    private val _vm: HomeViewModel by viewModels()
    private lateinit var _adapter: HomeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
    }

    private fun init() {
        SceneManager.create(
            SceneCreator.with(this)
                .add(Scene.MAIN, R.id.activity_home_recyclerview)
                .add(Scene.SPINNER, R.id.activity_home_progressbar)
                .add(Scene.PLACEHOLDER, R.id.activity_home_placeholder_text)
                .first(Scene.SPINNER)
        )

        _adapter = HomeRecyclerAdapter(_vm, this)
        activity_home_recyclerview.adapter = _adapter
        activity_home_recyclerview.layoutManager = LinearLayoutManager(this)
        activity_home_recyclerview.addItemDecoration(
            RecyclerMarginDecorator(
                resources,
                R.dimen.default_padding_double,
                R.dimen.default_padding,
                R.dimen.default_padding,
                R.dimen.default_padding_half
            )
        )
        activity_home_placeholder_text.setOnClickListener { _vm.handle(HomeAction.Retry) }
        activity_home_searchview.setOnQueryTextListener(this)
        _vm.state.map { it.scene }.bind(this, this::updateScene)
        _vm.state.map { it.data }.bind(this, this::updateData)
        _vm.state.map { it.errorException }.bind(this, this::onError)
        _vm.state.map { it.isPagingEnable }.bind(this, this::setAdapterPaging)
        _vm.handle(HomeAction.Load)
    }

    private fun setAdapterPaging(isPagingEnable: Boolean) {
        _adapter.isPagingEnabled = isPagingEnable
    }

    private fun onError(error: ErrorException?) {
        error?.showToast(this)
    }

    private fun updateScene(scene: Int) {
        SceneManager.scene(this, scene)
    }

    private fun updateData(data: Data<Hero>?) {
        data ?: return
        _adapter.setDataset(data.results)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // ignored
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        _vm.handle(HomeAction.Search(newText))
        return true
    }


    override fun onHeroClicked(hero: Hero) {
        HeroDetailsActivity.start(this, hero)
    }

    override fun onDestroy() {
        super.onDestroy()
        SceneManager.release(this)
    }
}
