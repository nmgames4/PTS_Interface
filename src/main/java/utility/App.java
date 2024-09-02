package utility;

import general.ReadInput;
import modules.PrintPts;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class App {
    HtmlUtility page;
    List<Member> members = new ArrayList<>();
    boolean programActive = true;

    public App(String pageSource) {
        page = new HtmlUtility(pageSource);
        loadMembersFromPts();
    }

    public void Run() {
        System.out.println("PTS loaded!\n");

        while (programActive) {
            System.out.println("""
                    Select an operation:
                      1) Show PTS
                      0) Exit Program
                    """);
            System.out.print("> ");
            switch (ReadInput.readInteger()) {
                case 1 -> PrintPts.run(members);
                case 0 -> programActive = false;
                default -> System.out.println("Invalid operation");
            }
        }

    }

    private void loadMembersFromPts() {
        page.getInnerHtmlFromSection(page.getBody(), "div.row").forEach(row -> {
            String payGrade = Jsoup.parse(row).select("span.r-id").text();
            page.getInnerHtmlFromSection(row, "div.card").forEach(member -> {
                members.add(MemberFactory.createMemberFromHtml(member, payGrade));
            });
        });
    }
}
