package usdf;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MemberFactory {

    // This class will act as a simple data holder for HTML elements
    public static class MemberHtmlElements {
        public Element nameElement;
        public Element subtitleElement;
        public Element serviceIdElement;
    }

    public static MemberHtmlElements getMemberHtmlElementsFromHtml(String htmlContent) {
        // Parse HTML content
        Document doc = Jsoup.parse(htmlContent);

        // Extract member name element and subtitle element
        Element nameElement = doc.select("h4.card-title a").first();
        Element serviceIdElement = doc.select("h4.card-title span").first();
        Element subtitleElement = doc.select("span.card-subtitle").first();

        // Create a holder object for the extracted elements
        MemberHtmlElements elements = new MemberHtmlElements();
        elements.nameElement = nameElement;
        elements.subtitleElement = subtitleElement;
        elements.serviceIdElement = serviceIdElement;

        return elements;
    }

    public static Member createMemberFromHtml(String htmlContent, String payGrade) {
        MemberHtmlElements elements = getMemberHtmlElementsFromHtml(htmlContent);
        String mottoUnmodified = elements.subtitleElement.text();

        String name, motto = elements.subtitleElement.text(), position, rank = "", featStack = "";
        int serviceId = Integer.parseInt(elements.serviceIdElement.text().substring(1));
        Branch branch = null;
        boolean command = false, selectee = false;

        // REMOVE ALT NAMES
        name = (elements.nameElement.text().contains("("))
                ? elements.nameElement.text().substring(0, elements.nameElement.text().indexOf("(") - 1)
                : elements.nameElement.text();

        // REMOVE FEAT STACK
        if (motto.contains("{")) {
            featStack = motto.substring(motto.indexOf("{") + 1, motto.indexOf("}"));
            motto = motto.substring(0, motto.indexOf("{") - 1);
        } else if (payGrade.startsWith("O") || payGrade.startsWith("ES") || payGrade.startsWith("RET")) {
            featStack = "5";
        }

        // RETRIEVE AND REMOVE BRANCH
        try {
            branch = Branch.getBranchFromFormat(motto.substring(1, motto.indexOf("]")));
        } catch (Exception e) {
            System.err.printf("%n%nAN ERROR OCCURRED REGISTERING THE BRANCH FOR:%n%s%n%s%n%n", name, motto);
        }
        motto = motto.substring(motto.indexOf("]") + 2); // e.g. motto = CW4, XO AT&E. {X4} + § (//)

        // REMOVE SERVICE STRIPES AND FURTHER
        motto = (motto.contains("(")) ? motto.substring(0, motto.indexOf("(") - 1) : motto;
        motto = (motto.contains("§")) ? motto.substring(0, motto.indexOf("§") - 1) : motto;
        motto = (motto.contains("+")) ? motto.substring(0, motto.indexOf("+") - 1) : motto;

        // EXTRACT RANK
        if (branch != Branch.CIVILIAN) {
            if (motto.contains(",")) {
                rank = motto.substring(0, motto.indexOf(","));
                motto = motto.substring(motto.indexOf(",") + 2, motto.indexOf("."));
                command = true;
            } else if (motto.contains("/")) {
                rank = motto.substring(0, motto.indexOf("/"));
                motto = motto.substring(motto.indexOf("/") + 1);
            } else if (motto.contains(" ")) {
                rank = motto.substring(0, motto.indexOf(" "));
                motto = motto.substring(motto.indexOf(" ") + 1);
            } else {
                rank = motto;
                motto = "";
            }
        }

        // DETERMINE IF SELECTEE AND REMOVE SEL
        if (motto.startsWith("SEL")) {
            selectee = true;
            motto = (motto.length() != 3) ? motto.substring(4) : "";
        }

        // ASSIGN POSITION
        if (motto.isEmpty()) {
            position = "None";
        } else {
            position = motto;
            motto = motto.replace(position, "");
        }

        // CLEANUP FOR CIVIL SERVICE
        if (branch == Branch.CIVILIAN) rank = position;

//        assert branch != null; // Program should throw a fatal exception if this errors.
//        System.out.printf(
//                "%-5s | %-4s | %-20s | %-45s | %-4s | %-15s | %-15s | %-12s | Debug = %s %n",
//                serviceId,
//                payGrade,
//                name,
//                mottoUnmodified,
//                branch.getAbbreviation(),
//                rank,
//                (position.equals("None")) ? "" : position,
//                featStack,
//                motto
//        );

        return new Member(
                name,
                serviceId,
                branch,
                payGrade,
                rank,
                command,
                selectee,
                mottoUnmodified,
                position,
                featStack
        );
    }
}