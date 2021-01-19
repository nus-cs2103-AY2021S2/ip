import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String line = String.format("%" + 5 + "s", " ") + "____________________________________________________________________";
        System.out.println(line);
        String logo = "\n" +
                "     ███╗░░░███╗░█████╗░██╗░░░██╗███╗░░░███╗░█████╗░██╗░░░██╗\n" +
                "     ████╗░████║██╔══██╗██║░░░██║████╗░████║██╔══██╗██║░░░██║\n" +
                "     ██╔████╔██║██║░░██║██║░░░██║██╔████╔██║██║░░██║██║░░░██║\n" +
                "     ██║╚██╔╝██║██║░░██║██║░░░██║██║╚██╔╝██║██║░░██║██║░░░██║\n" +
                "     ██║░╚═╝░██║╚█████╔╝╚██████╔╝██║░╚═╝░██║╚█████╔╝╚██████╔╝\n" +
                "     ╚═╝░░░░░╚═╝░╚════╝░░╚═════╝░╚═╝░░░░░╚═╝░╚════╝░░╚═════╝░";
        System.out.println(logo + " is back!\n     What have you awoken MouMou for?");
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String toPrint = "";
        String input = sc.nextLine();
        List<String> list = new ArrayList<>();
        while (!input.equals("bye")) { //user input is not bye
            System.out.println(line);
            if (input.equals("list")) {
                toPrint = ""; //reinitialising toPrint
                for (int i = 1; i < list.size() + 1; i++) {
                    String point = i + ". " + list.get(i - 1);
                    if (i != list.size()) {
                        toPrint = toPrint + align(point) + "\n";
                    } else {
                        toPrint = toPrint + align(point);
                    }
                }
                System.out.println(toPrint);
            } else {
                list.add(input);
                toPrint = "added: " + input;
                System.out.println(align(toPrint));
            }
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println(align("Goodnight! MouMou will go back to sleep now."));
        System.out.println(line);
    }

    static String align(String s) { // to align
        int stringLength = s.length();
        int LINELENGTH = 73;
        int left = (int) Math.ceil((LINELENGTH - stringLength) / 2);
        int right = (int) Math.floor((LINELENGTH - stringLength) / 2);
        String newString = "";
        newString = String.format("%" + left + "s", " ") + s + String.format("%" + right + "s", " ");
        return newString;
    }
}
