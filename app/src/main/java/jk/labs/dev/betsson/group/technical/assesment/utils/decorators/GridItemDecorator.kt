package jk.labs.dev.betsson.group.technical.assesment.utils.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridItemDecorator(private val space: Int) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.top = if (parent.getChildLayoutPosition(view) in listOf(0, 1)) space * 4 else 0
        outRect.left =
            if (parent.getChildLayoutPosition(view) % 2 in listOf(0, 1)) space / 2 else space
        outRect.right = space / 2 //if (parent.getChildLayoutPosition(view) % 2 == 0) space / 2 else space

        parent.adapter?.let {
            outRect.bottom =
                if (parent.getChildLayoutPosition(view) in listOf(
                        it.itemCount - 1,
                        it.itemCount - 2
                    )
                ) space * 6 else space
        }
    }
}