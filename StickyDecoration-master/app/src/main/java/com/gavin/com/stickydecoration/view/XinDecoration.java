package com.gavin.com.stickydecoration.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ykhuang on 2017/10/26.
 */

public class XinDecoration extends RecyclerView.ItemDecoration {
    private View mHeadView;
    public XinDecoration(View headView){
        this.mHeadView = headView;
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mHeadView.setLayoutParams(layoutParams);
        mHeadView.setDrawingCacheEnabled(true);
        mHeadView.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //指定高度、宽度的groupView
//        mHeadView.layout(0, 0, right, mGroupHeight);
        mHeadView.buildDrawingCache();
        Bitmap bitmap = mHeadView.getDrawingCache();
        c.drawBitmap(bitmap, 0, 0, null);
    }
}
