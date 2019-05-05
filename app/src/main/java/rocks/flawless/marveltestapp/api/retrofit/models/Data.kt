package rocks.flawless.marveltestapp.api.retrofit.models

import com.google.gson.annotations.SerializedName

data class Data<T>(
    @SerializedName("offset") val offset: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("results") val results: List<T>
) {
    companion object {
        fun <T> mergeData(oldData: Data<T>?, newData: Data<T>?): Data<T>? = when {
            oldData == null -> newData
            newData == null -> oldData
            else -> Data(
                newData.offset,
                newData.total,
                newData.count,
                oldData.results + newData.results
            )
        }
    }
}