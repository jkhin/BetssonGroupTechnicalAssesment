package jk.labs.dev.betsson.group.technical.assesment.utils.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import jk.labs.dev.betsson.group.technical.assesment.R

class PlacePhotoItemDecorator(private val space: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        parent.adapter?.let {
            outRect.top = space
            outRect.left =
                if (parent.getChildLayoutPosition(view) == 0) space * 2 else space
            outRect.right =
                if (parent.getChildLayoutPosition(view) == it.itemCount - 1) space * 2 else space
            outRect.bottom = space
        }
    }
}