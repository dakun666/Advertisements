package com.wantupai.advertisements.login.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wantupai.advertisements.R;

/**
 * Created by xuedakun on 2019-12-23 14:41
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class DotArrayView extends View {

    private static final int DEFAULT_LINES = 3;
    private static final int DEFAULT_COLUMNS = 3;
    private static final int DEFAULT_DOT_COLOR = Color.BLACK;
    private static final int DEFAULT_DOT_DIAM = 10;
    private static final int DEFAULT_DOT_SPACE = 10;

    private int lines = DEFAULT_LINES;

    private int columns = DEFAULT_COLUMNS;

    private int dotColor = DEFAULT_DOT_COLOR;

    private float dotDiam = DEFAULT_DOT_DIAM;

    private float dotSpace = DEFAULT_DOT_SPACE;

    private Paint dotPaint;

    public DotArrayView(Context context) {
        this(context, null);
    }

    public DotArrayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotArrayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DotArrayView,
                defStyleAttr, 0);
        if (attributes != null) {
            lines = attributes.getInteger(R.styleable.DotArrayView_lines, DEFAULT_LINES);
            columns = attributes.getInteger(R.styleable.DotArrayView_columns, DEFAULT_COLUMNS);
            dotColor = attributes.getColor(R.styleable.DotArrayView_dotColor, DEFAULT_DOT_COLOR);
            dotDiam = attributes.getDimension(R.styleable.DotArrayView_dotDiam, DEFAULT_DOT_DIAM);
            dotSpace = attributes.getDimension(R.styleable.DotArrayView_dotSpace, DEFAULT_DOT_SPACE);
            attributes.recycle();
        }

        dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotPaint.setColor(dotColor);
        dotPaint.setStrokeWidth(dotDiam);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int measuredWidth = 0, measuredHeight = 0;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            for (int columnsTemp = columns; columnsTemp > 0; columnsTemp--) {
                measuredWidth += dotDiam;
                if (columnsTemp != 0) {
                    measuredWidth += dotSpace;
                }
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            for (int linesTemp = lines; linesTemp > 0; linesTemp--) {
                measuredHeight += dotDiam;
                if (linesTemp != 0) {
                    measuredHeight += dotSpace;
                }
            }
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int line = 0; line < lines; line++) {
            for (int column = 0; column < columns; column++) {
                canvas.drawCircle((dotDiam + dotSpace) * column + (dotDiam / 2), (dotDiam + dotSpace) * line + (dotDiam / 2), dotDiam / 2, dotPaint);
            }
        }
    }
}
