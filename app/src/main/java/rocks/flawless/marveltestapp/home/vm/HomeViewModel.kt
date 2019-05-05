package rocks.flawless.marveltestapp.home.vm

import android.text.TextUtils
import com.geronimostudios.coffeescene.annotations.Scene
import kotlinx.coroutines.delay
import rocks.flawless.marveltestapp.api.Api
import rocks.flawless.marveltestapp.api.Api.safeCall
import rocks.flawless.marveltestapp.api.ErrorException
import rocks.flawless.marveltestapp.api.retrofit.models.Data
import rocks.flawless.marveltestapp.base.BaseViewModel
import rocks.flawless.marveltestapp.home.action.HomeAction

class HomeViewModel(
    initialState: HomeState = HomeState(scene = Scene.SPINNER)
) : BaseViewModel<HomeState, HomeAction>(initialState) {

    private var lastAction: HomeAction? = null

    companion object {
        private const val QUERY_DELAY_IN_MILLIS = 500L
    }

    override fun handle(action: HomeAction) {
        when (action) {
            is HomeAction.Retry -> retry()
            is HomeAction.Load -> loadCharacters()
            is HomeAction.LoadMore -> loadMoreCharacters()
            is HomeAction.Search -> search(action.queryText)
        }
        if (action !is HomeAction.Retry) {
            lastAction = action
        }
    }

    private fun retry() {
        updateState { state.value!!.copy(scene = Scene.SPINNER, errorException = null) }
        handle(lastAction!!)
    }

    private fun loadCharacters(cancel: Boolean = true) = launchVm(cancel) {
        var currentState = state.value!!
        if (currentState.scene != Scene.SPINNER) {
            updateState { currentState.copy(scene = Scene.SPINNER) }
        }
        currentState = state.value!!
        try {
            val dataResponse = safeCall { Api.characterService.getCharacters(name = currentState.query) }
            with(dataResponse) {
                updateState {
                    currentState.copy(
                        scene = Scene.MAIN,
                        data = data,
                        isPagingEnable = data.count != data.total,
                        errorException = null
                    )
                }
            }
        } catch (e: ErrorException) {
            updateState { currentState.copy(scene = Scene.PLACEHOLDER, errorException = e) }
        }
    }

    private fun loadMoreCharacters(cancel: Boolean = true) = launchVm(cancel) {
        val currentState = state.value!!
        try {
            with(currentState.data!!) {
                if (offset >= total) {
                    return@launchVm
                }
                val dataResponse = safeCall {
                    Api.characterService.getCharacters(
                        name = currentState.query,
                        offset = offset + currentState.data.count
                    )
                }
                val data = Data.mergeData(currentState.data, dataResponse.data)!!
                updateState {
                    currentState.copy(
                        scene = Scene.MAIN,
                        data = data,
                        isPagingEnable = data.results.size != data.total
                    )
                }
            }
        } catch (e: ErrorException) {
            updateState { currentState.copy(scene = Scene.PLACEHOLDER, errorException = e) }
        }
    }

    private fun search(query: String?) = launchVm {
        delay(QUERY_DELAY_IN_MILLIS)
        val currentState = state.value!!
        updateState {
            currentState.copy(
                query = if (TextUtils.isEmpty(query)) null else query
            )
        }
        loadCharacters(false)
    }
}