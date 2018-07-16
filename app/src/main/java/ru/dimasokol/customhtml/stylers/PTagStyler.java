package ru.dimasokol.customhtml.stylers;

import android.text.Editable;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class PTagStyler extends BlockTagStyler {

    public static final String TAG = "p";

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void applyStyle(Editable html, int startPosition, int endPosition) {

    }
}
