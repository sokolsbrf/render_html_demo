package ru.dimasokol.customhtml.css;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import ru.dimasokol.customhtml.stylers.TagStyler;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class ColorStyler extends TagStyler {

    private String mColor;
    private int mColorInt;

    public ColorStyler(String color) {
        mColor = color;
        mColorInt = Color.parseColor(color);
    }

    @Override
    public String getTag() {
        return null;
    }

    @Override
    public void applyStyle(Editable html, int startPosition, int endPosition) {
        ForegroundColorSpan span = new ForegroundColorSpan(mColorInt);
        html.setSpan(span, startPosition, endPosition, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

}
