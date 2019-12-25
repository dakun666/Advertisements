package com.wantupai.advertisements.login.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.AppCompatEditText;

import com.wantupai.advertisements.R;

/**
 * Created by xuedakun on 2019-12-24 13:30
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class VerifyCodeEdit extends AppCompatEditText {

    public interface VerifyCodeInputFinishListener {
        void onInputFinish(String verifyCode);
    }

    private static final int DEFAULT_CODE_LENGTH = 3;
    private static final int DEFAULT_EDIT_WIDTH = 100;
    private static final int DEFAULT_EDIT_HEIGHT = 100;
    private static final int DEFAULT_EDIT_MARGIN = 30;

    private int codeLength = DEFAULT_CODE_LENGTH;
    private float editWidth = DEFAULT_EDIT_WIDTH;
    private float editHeight = DEFAULT_EDIT_HEIGHT;
    private float editMargin = DEFAULT_EDIT_MARGIN;
    private Drawable editBackground;

    private int textColor;

    private VerifyCodeInputFinishListener inputFinishListener;

    private final Rect mRect = new Rect();

    public VerifyCodeEdit(Context context) {
        this(context, null);
    }

    public VerifyCodeEdit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyCodeEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VerifyCodeEdit,
                defStyleAttr, 0);
        if (attributes != null) {
            codeLength = attributes.getInteger(R.styleable.VerifyCodeEdit_codeLength, DEFAULT_CODE_LENGTH);
            editWidth = attributes.getDimension(R.styleable.VerifyCodeEdit_editWidth, DEFAULT_EDIT_WIDTH);
            editHeight = attributes.getDimension(R.styleable.VerifyCodeEdit_editHeight, DEFAULT_EDIT_HEIGHT);
            editMargin = attributes.getDimension(R.styleable.VerifyCodeEdit_editMargin, DEFAULT_EDIT_MARGIN);
            editBackground = attributes.getDrawable(R.styleable.VerifyCodeEdit_editBackground);
            attributes.recycle();
        }

        setMaxLength(codeLength);
        setLongClickable(false);
        setBackgroundColor(Color.TRANSPARENT);
        setCursorVisible(false);
        setFocusableInTouchMode(true);
        setFocusable(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            height = (int) editHeight;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            width = (int) (editWidth * codeLength + editMargin * (codeLength - 1));
        }

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, widthMode);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, heightMode);

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawStrokeBackground(canvas);
        drawText(canvas);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        return false;
    }

    @Override
    public int getOffsetForPosition(float x, float y) {
        return getText().length();
    }

    @Override
    protected MovementMethod getDefaultMovementMethod() {
        return null;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        int textLength = getEditableText().length();
        if (textLength == codeLength) {
            if (inputFinishListener != null) {
                inputFinishListener.onInputFinish(getEditableText().toString());
            }
        }
    }

    public void setInputFinishListener(VerifyCodeInputFinishListener inputFinishListener) {
        this.inputFinishListener = inputFinishListener;
    }

    /**
     * 设置最大长度
     */
    private void setMaxLength(int maxLength) {
        if (maxLength >= 0) {
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        } else {
            setFilters(new InputFilter[0]);
        }
    }

    /**
     * 绘制方框
     */
    private void drawStrokeBackground(Canvas canvas) {
        // 下面绘制方框背景颜色
        // 确定反馈位置
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = (int) editWidth;
        mRect.bottom = (int) editHeight;
        int count = canvas.getSaveCount(); //  当前画布保存的状态
        canvas.save(); // 保存画布
        for (int i = 0; i < codeLength; i++) {
            editBackground.setBounds(mRect); // 设置位置
            editBackground.setState(new int[]{android.R.attr.state_enabled}); // 设置图像状态
            editBackground.draw(canvas); //  画到画布上
            //  确定下一个方框的位置
            float dx = mRect.right + editMargin; // X坐标位置
            // 保存画布
            canvas.save();
            // [注意细节] 移动画布到下一个位置
            canvas.translate(dx, 0);
        }
        // [注意细节] 把画布还原到画反馈之前的状态，这样就还原到最初位置了
        canvas.restoreToCount(count);
        // 画布归位
        canvas.translate(0, 0);

        // 下面绘制高亮状态的边框
        // 当前高亮的索引
        int activatedIndex = Math.max(0, getEditableText().length());
        mRect.left = (int) (editWidth * activatedIndex + editMargin * activatedIndex);
        mRect.right = (int) (mRect.left + editWidth);
        editBackground.setState(new int[]{android.R.attr.state_focused});
        editBackground.setBounds(mRect);
        editBackground.draw(canvas);

    }

    /**
     * 重绘文本
     */
    private void drawText(Canvas canvas) {
        int count = canvas.getSaveCount();
        canvas.translate(0, 0);
        int length = getEditableText().length();
        for (int i = 0; i < length; i++) {
            String text = String.valueOf(getEditableText().charAt(i));
            TextPaint textPaint = getPaint();
            textPaint.setColor(getCurrentTextColor());
            // 获取文本大小
            textPaint.getTextBounds(text, 0, 1, mRect);
            // 计算(x,y) 坐标
            int x = (int) (editWidth / 2 + (editWidth + editMargin) * i - (mRect.centerX()));
            int y = canvas.getHeight() / 2 + mRect.height() / 2;
            canvas.drawText(text, x, y, textPaint);
        }
        canvas.restoreToCount(count);
    }
}
