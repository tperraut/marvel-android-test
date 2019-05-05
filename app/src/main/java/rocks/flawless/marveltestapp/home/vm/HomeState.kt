package rocks.flawless.marveltestapp.home.vm

import rocks.flawless.marveltestapp.api.ErrorException
import rocks.flawless.marveltestapp.api.retrofit.models.Data
import rocks.flawless.marveltestapp.api.retrofit.models.Hero
import rocks.flawless.marveltestapp.base.State

data class HomeState(
    val data: Data<Hero>? = null,
    val query: String? = null,
    val scene: Int,
    val isPagingEnable: Boolean = true,
    val errorException: ErrorException? = null
) : State