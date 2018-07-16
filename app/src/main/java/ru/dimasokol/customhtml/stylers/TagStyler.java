package ru.dimasokol.customhtml.stylers;

import android.text.Editable;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public abstract class TagStyler {

    public abstract String getTag();

    public abstract void applyStyle(Editable html, int startPosition, int endPosition);

    public boolean isBlock() {
        return false;
    }

}
