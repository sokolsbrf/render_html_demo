package ru.dimasokol.customhtml.stylers;

import android.content.Context;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class H1TagStyler extends BlockTagStyler {

    public static final String TAG = "h1";

    private Context mContext;

    public H1TagStyler(Context context) {
        mContext = context;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void applyStyle(Editable html, int startPosition, int endPosition) {

        AbsoluteSizeSpan span = new AbsoluteSizeSpan((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PT, 24, mContext.getResources().getDisplayMetrics()));

        html.setSpan(span, startPosition, endPosition, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}
