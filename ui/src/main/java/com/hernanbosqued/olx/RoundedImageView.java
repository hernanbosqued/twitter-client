package com.hernanbosqued.olx;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

public class RoundedImageView extends android.support.v7.widget.AppCompatImageView {
    private static final float BORDER_WIDTH_PROPORTIONAL_MULTIPLIER = .05f;
    private final Path path;
    private RectF rect;

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rect = new RectF( );
        rect.left = 0;
        rect.top = 0;
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float cornerRadius = ( getWidth() > getHeight() ? getHeight():getWidth() ) * BORDER_WIDTH_PROPORTIONAL_MULTIPLIER;
        rect.bottom = getHeight();
        rect.right = getWidth();
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
