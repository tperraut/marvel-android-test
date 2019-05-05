package rocks.flawless.marveltestapp.home.action

import rocks.flawless.marveltestapp.base.Action

sealed class HomeAction : Action {
    object Retry : HomeAction()
    object Load : HomeAction()
    object LoadMore : HomeAction()
    data class Search(val queryText: String?) : HomeAction()
}