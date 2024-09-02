package utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HtmlUtility {

    private Document document;

    /**
     * Constructor to initialize the HtmlUtility with HTML content.
     *
     * @param html The HTML content as a string.
     */
    public HtmlUtility(String html) {
        this.document = Jsoup.parse(html);
    }

    /**
     * Extract the header section from the HTML.
     *
     * @return The HTML of the header section as a string.
     */
    public String getHeader() {
        Element head = document.head();
        return head.outerHtml();
    }

    /**
     * Extract the body section from the HTML.
     *
     * @return The HTML of the body section as a string.
     */
    public String getBody() {
        Element body = document.body();
        return body.outerHtml();
    }

    /**
     * Get a list of elements of a specified type from a given HTML section.
     *
     * @param htmlSection The HTML section as a string.
     * @param elementTag The CSS selector for the elements to extract (e.g., "h2.rank-title").
     * @return A list of strings, each containing the HTML of an occurrence of the specified element.
     */
    public List<String> getOuterHtmlFromSection(String htmlSection, String elementTag) {
        Document sectionDoc = Jsoup.parse(htmlSection);
        Elements elements = sectionDoc.select(elementTag);

        List<String> result = new ArrayList<>();
        for (Element el : elements) {
            result.add(el.outerHtml());
        }

        return result;
    }

    /**
     * Get a list of the inner HTML content of elements of a specified type from a given HTML section.
     *
     * @param htmlSection The HTML section as a string.
     * @param elementTag The CSS selector for the elements to extract (e.g., "h2.rank-title").
     * @return A list of strings, each containing the inner HTML of an occurrence of the specified element, excluding the outer tags.
     */
    public List<String> getInnerHtmlFromSection(String htmlSection, String elementTag) {
        Document sectionDoc = Jsoup.parse(htmlSection);
        Elements elements = sectionDoc.select(elementTag);

        List<String> result = new ArrayList<>();
        for (Element el : elements) {
            result.add(el.html()); // Use html() to get the inner HTML of the element
        }

        return result;
    }
}
