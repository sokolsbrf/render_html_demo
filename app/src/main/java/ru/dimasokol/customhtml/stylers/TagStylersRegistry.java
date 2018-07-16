package ru.dimasokol.customhtml.stylers;

import android.content.Context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class TagStylersRegistry {

    private Map<String, TagStyler> mStylers = new HashMap<>();

    public TagStylersRegistry(Context context) {
        mStylers.put(H1TagStyler.TAG, new H1TagStyler(context));
        mStylers.put(H2TagStyler.TAG, new H2TagStyler(context));
        mStylers.put(H3TagStyler.TAG, new H3TagStyler(context));
        mStylers.put(ITagStyler.TAG, new ITagStyler());
        mStylers.put(BTagStyler.TAG, new BTagStyler());
        mStylers.put(UlTagStyler.TAG, new UlTagStyler());
        mStylers.put(LiTagStyler.TAG, new LiTagStyler());
        mStylers.put(PTagStyler.TAG, new PTagStyler());
    }

    public Map<String, TagStyler> getStylers() {
        return Collections.unmodifiableMap(mStylers);
    }
}
