package ru.dimasokol.customhtml.stylers;

import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.StyleSpan;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class ITagStyler extends TagStyler {

    public static final String TAG = "i";

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void applyStyle(Editable html, int startPosition, int endPosition) {
        StyleSpan span = new StyleSpan(Typeface.ITALIC);
        html.setSpan(span, startPosition, endPosition, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}
