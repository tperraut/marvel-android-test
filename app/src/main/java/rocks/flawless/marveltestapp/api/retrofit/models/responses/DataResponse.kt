package rocks.flawless.marveltestapp.api.retrofit.models.responses

import com.google.gson.annotations.SerializedName
import rocks.flawless.marveltestapp.api.retrofit.models.Data

data class DataResponse<T>(
    @SerializedName("data") val data: Data<T>
)