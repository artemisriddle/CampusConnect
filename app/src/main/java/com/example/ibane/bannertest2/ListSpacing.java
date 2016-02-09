package com.example.ibane.bannertest2;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListSpacing extends RecyclerView.ItemDecoration {

    private final int mVerticalSpaceHeight;
    private final int mHorizontalMargin;

    public ListSpacing(int mVerticalSpaceHeight, int mHorizontalMargin) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        this.mHorizontalMargin = mHorizontalMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
        outRect.left = mHorizontalMargin;
        outRect.right = mHorizontalMargin;
        outRect.height();
    }
}