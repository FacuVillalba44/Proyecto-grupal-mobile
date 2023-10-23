package com.pteplus.petplus.item_decorador;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;

public class DivisorItem extends RecyclerView.ItemDecoration {
    private Paint paint;
    private int dividerHeight;

    public DivisorItem(@ColorInt int dividerColor, int dividerHeight) {
        paint = new Paint();
        paint.setColor(dividerColor);
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom();
            int bottom = top + dividerHeight;
            c.drawRect(left, top, right, bottom, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = dividerHeight;
    }
}