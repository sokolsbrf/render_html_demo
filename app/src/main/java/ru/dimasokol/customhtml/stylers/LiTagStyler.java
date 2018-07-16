package ru.dimasokol.customhtml.stylers;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.BulletSpan;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class LiTagStyler extends BlockTagStyler {

    public static final String TAG = "li";

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void applyStyle(Editable html, int startPosition, int endPosition) {
        BulletSpan span = new BulletSpan(40, Color.GREEN);
        html.setSpan(span, startPosition, endPosition, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}
