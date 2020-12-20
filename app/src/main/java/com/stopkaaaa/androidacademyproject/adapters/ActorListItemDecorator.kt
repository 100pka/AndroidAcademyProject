package com.stopkaaaa.androidacademyproject.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ActorListItemDecorator(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            right = if (parent.getChildAdapterPosition(view)  == state.itemCount - 1) {
                0
            } else {
                margin
            }
            top = 0
            left = 0
            bottom = 0
        }
    }
}