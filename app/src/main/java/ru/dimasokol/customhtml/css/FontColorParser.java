package ru.dimasokol.customhtml.css;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class FontColorParser {

    private static final Pattern FONT_COLOR = Pattern.compile("color:\\W*([#01234567890abcdefABCDEF]{7});");

    public String getColor(String styleAttr) {
        Matcher matcher = FONT_COLOR.matcher(styleAttr);

        if (matcher.matches()) {
            return matcher.group(matcher.groupCount());
        }

        return null;
    }
}
