package au.com.roadhouse.licensehelper.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalDividerItemDecorator extends RecyclerView.ItemDecoration {

    private final int mDividerColor;
    private final Drawable mDividerColorDrawable;
    private final int mDividerHeight;

    public VerticalDividerItemDecorator(Context context, @DimenRes int dividerHeight, @ColorRes int dividerColor){
        mDividerHeight = context.getResources().getDimensionPixelOffset(dividerHeight);
        mDividerColor = ResourcesCompat.getColor(context.getResources(), dividerColor, context.getTheme());
        mDividerColorDrawable = new ColorDrawable(mDividerColor);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.Adapter adapter  = parent.getAdapter();
        if(parent.getChildAdapterPosition(view) != 0 && parent.getChildAdapterPosition(view) < adapter.getItemCount()){
            outRect.set(0, 0, 0, mDividerHeight);
        } else {
            outRect.setEmpty();
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.Adapter adapter  = parent.getAdapter();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            if(parent.getChildAdapterPosition(view) != 0 && parent.getChildAdapterPosition(view) < adapter.getItemCount()){
                mDividerColorDrawable.setBounds(0, view.getTop() + (int)view.getTranslationY() - mDividerHeight, parent.getWidth(), view.getTop() + (int)view.getTranslationY());
                mDividerColorDrawable.draw(c);
            }
        }
    }
}
