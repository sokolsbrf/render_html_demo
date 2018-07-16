package ru.dimasokol.customhtml.stylers;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public abstract class BlockTagStyler extends TagStyler {

    @Override
    public boolean isBlock() {
        return true;
    }
}
