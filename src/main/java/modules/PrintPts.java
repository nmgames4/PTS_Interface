package modules;

import general.ReadInput;
import utility.Member;

import java.util.List;

public class PrintPts {
    public static void run(List<Member> members) {
        boolean loop = true;
        while (loop) {
            System.out.println("""
                    Select an operation:
                      1) Print compact PTS
                      2) Print fancy PTS
                      0) Return
                    """);
            System.out.print("> ");
            switch (ReadInput.readInteger()) {
                case 1 -> PrintPts.printCompact(members);
                case 2 -> PrintPts.printFancy(members);
                case 0 -> loop = false;
            }
        }
    }

    private static void printCompact(List<Member> members) {
        members.forEach(m -> System.out.println(m.toStringSingleLine()));
    }

    private static void printFancy(List<Member> members) {
        members.forEach(m -> System.out.println(m.toString()));
    }
}
