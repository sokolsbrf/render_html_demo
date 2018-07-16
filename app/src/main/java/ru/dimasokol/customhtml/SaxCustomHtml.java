package ru.dimasokol.customhtml;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ru.dimasokol.customhtml.css.ColorStyler;
import ru.dimasokol.customhtml.css.FontColorParser;
import ru.dimasokol.customhtml.stylers.BTagStyler;
import ru.dimasokol.customhtml.stylers.H1TagStyler;
import ru.dimasokol.customhtml.stylers.H2TagStyler;
import ru.dimasokol.customhtml.stylers.ITagStyler;
import ru.dimasokol.customhtml.stylers.LiTagStyler;
import ru.dimasokol.customhtml.stylers.PTagStyler;
import ru.dimasokol.customhtml.stylers.TagStyler;
import ru.dimasokol.customhtml.stylers.UlTagStyler;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class SaxCustomHtml {

    private SAXParser mParser;
    private Map<String, TagStyler> mStylers = new HashMap<>();

    public SaxCustomHtml(Context context) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            mParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        mStylers.put(H1TagStyler.TAG, new H1TagStyler(context));
        mStylers.put(H2TagStyler.TAG, new H2TagStyler(context));
        mStylers.put(ITagStyler.TAG, new ITagStyler());
        mStylers.put(BTagStyler.TAG, new BTagStyler());
        mStylers.put(UlTagStyler.TAG, new UlTagStyler());
        mStylers.put(LiTagStyler.TAG, new LiTagStyler());
        mStylers.put(PTagStyler.TAG, new PTagStyler());
    }

    public Spanned fromHtml(String html) {
        ByteArrayInputStream stream = new ByteArrayInputStream(html.getBytes());
        TagHandler handler = new TagHandler();

        try {
            mParser.parse(stream, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return handler.getResult();
    }

    private class TagHandler extends DefaultHandler {

        private SpannableStringBuilder mResult = new SpannableStringBuilder();
        private Map<String, Integer> mTagStarts = new HashMap<>();
        private Map<String, String> mStyles = new HashMap<>();
        private FontColorParser mFontColorParser = new FontColorParser();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            String tag = localName.toLowerCase();

            TagStyler styler = mStylers.get(tag);
            if (styler != null) {
                if (styler.isBlock() && mResult.length() > 0) {
                    mResult.append("\n");
                }
                mTagStarts.put(tag, mResult.length());
                mStyles.put(tag, attributes.getValue("style"));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            String tag = localName.toLowerCase();

            TagStyler styler = mStylers.get(tag);
            if (styler != null && mTagStarts.containsKey(tag)) {
                int start = mTagStarts.get(tag);
                styler.applyStyle(mResult, start, mResult.length());
                mTagStarts.remove(tag);

                String styleAttr = mStyles.get(tag);
                mStyles.remove(tag);

                if (styleAttr != null) {
                    applyColor(mFontColorParser.getColor(styleAttr), start);
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            mResult.append(new String(ch, start, length));
        }

        public SpannableStringBuilder getResult() {
            return mResult;
        }

        private void applyColor(String color, int start) {
            if (color != null) {
                ColorStyler styler = new ColorStyler(color);
                styler.applyStyle(mResult, start, mResult.length());
            }
        }
    }

}
