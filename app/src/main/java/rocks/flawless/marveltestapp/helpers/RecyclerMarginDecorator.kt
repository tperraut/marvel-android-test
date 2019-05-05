package rocks.flawless.marveltestapp.helpers

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

data class RecyclerMarginDecorator(
    val marginTop: Int,
    val marginStart: Int,
    val marginStartBetween: Int,
    val marginTopBetween: Int,
    val marginBottom: Int = marginTop,
    val marginBottomBetween: Int = marginTopBetween,
    val marginEnd: Int = marginStart,
    val marginEndBetween: Int = marginStartBetween
) : RecyclerView.ItemDecoration() {

    constructor(
        margin: Int,
        @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL
    ) : this(
        margin,
        margin,
        if (orientation == RecyclerView.HORIZONTAL) margin / 2 else margin,
        if (orientation == RecyclerView.VERTICAL) margin / 2 else margin
    )

    constructor(
        res: Resources,
        @DimenRes marginRes: Int,
        @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL
    ) : this(
        res.getDimension(marginRes).toInt(),
        orientation
    )

    constructor(
        res: Resources,
        @DimenRes marginResTop: Int,
        @DimenRes marginResStart: Int,
        @DimenRes marginResStartBetween: Int,
        @DimenRes marginResTopBetween: Int,
        @DimenRes marginResBottom: Int = marginResTop,
        @DimenRes marginResBottomBetween: Int = marginResTopBetween,
        @DimenRes marginResEnd: Int = marginResStart,
        @DimenRes marginResEndBetween: Int = marginResStartBetween
    ) : this(
        res.getDimension(marginResTop).toInt(),
        res.getDimension(marginResStart).toInt(),
        res.getDimension(marginResStartBetween).toInt(),
        res.getDimension(marginResTopBetween).toInt(),
        res.getDimension(marginResBottom).toInt(),
        res.getDimension(marginResBottomBetween).toInt(),
        res.getDimension(marginResEnd).toInt(),
        res.getDimension(marginResEndBetween).toInt()
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val currentPosition: Int = parent.getChildAdapterPosition(view)
        when (currentPosition) {
            0 -> {
                outRect.top = marginTop
                outRect.left = marginStart
            }
            else -> {
                outRect.top = marginTopBetween
                outRect.left = marginStartBetween
            }
        }
        when (currentPosition) {
            state.itemCount - 1 -> {
                outRect.bottom = marginBottom
                outRect.right = marginEnd
            }
            else -> {
                outRect.bottom = marginBottomBetween
                outRect.right = marginEndBetween
            }
        }
    }
}