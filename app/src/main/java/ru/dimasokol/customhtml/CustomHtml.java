package ru.dimasokol.customhtml;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.List;
import java.util.Map;

import ru.dimasokol.customhtml.stylers.TagStyler;
import ru.dimasokol.customhtml.stylers.TagStylersRegistry;

/**
 * @author Дмитрий Соколов <DPSokolov.SBT@sberbank.ru>
 */
public class CustomHtml {

    private final Map<String, TagStyler> mStylers;
    private int mTextBlocksCount = 0;

    public CustomHtml(Context context) {
        TagStylersRegistry registry = new TagStylersRegistry(context);
        mStylers = registry.getStylers();
    }

    public Spanned fromHtml(String html) {

        Document document = Jsoup.parse(html);
        Element body = document.body();

        SpannableStringBuilder result = new SpannableStringBuilder();

        if (body != null) {
            proceedElement(body, result);
        }

        return result;
    }

    private void proceedElement(Element element, SpannableStringBuilder output) {

        int start = output.length();
        traverseNodes(element.childNodes(), output);
        int end = output.length();

        TagStyler styler = mStylers.get(element.tagName().toLowerCase());

        if (styler != null) {
            if (styler.isBlock()) {
                output.append("\n");

                if (mTextBlocksCount > 1) {
                    output.insert(start, "\n");
                    start++;
                    end++;
                }
            }

            styler.applyStyle(output, start, end);
        }

    }


    private void traverseNodes(List<Node> nodes, SpannableStringBuilder output) {

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);

            if (node instanceof TextNode) {
                String text = ((TextNode) node).text();
                output.append(text);
                mTextBlocksCount++;
            } else {
                proceedElement((Element) node, output);
                mTextBlocksCount = 0;
            }
        }

    }

}
