package com.stopkaaaa.androidacademyproject.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MovieListItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            bottom = 2 * margin
            if (parent.getChildAdapterPosition(view) % 2 == 0) {
                right = margin
                left = 0
            } else {
                right = 0
                left = margin
            }
            top = 0
        }
    }
}