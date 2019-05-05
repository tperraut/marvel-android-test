package rocks.flawless.marveltestapp.api.retrofit.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    @SerializedName("path") val path: String?,
    @SerializedName("extension") val extension: String?
) : Parcelable {
    companion object {
        const val SIZE_SMALL = "standard_small"
        const val SIZE_LARGE = "standard_xlarge"
    }

    fun getImgUrlBySize(size: String = SIZE_SMALL) = when (size) {
        SIZE_SMALL, SIZE_LARGE -> "$path/$size.$extension"
        else -> throw IllegalArgumentException("invalid size")
    }
}