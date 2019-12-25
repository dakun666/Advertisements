package com.wantupai.advertisements.login.view.span;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

/**
 * Created by xuedakun on 2019-12-24 16:13
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public abstract class ClickableColorSpan extends ClickableSpan {

    private int color;

    public ClickableColorSpan(int color) {
        this.color = color;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(color);
        ds.setUnderlineText(false);
    }
}
