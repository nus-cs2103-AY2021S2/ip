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
        String input = sc.next();
        while (!input.equals("bye")) { //user input is not bye
            System.out.println(line);
            toPrint = input;
            System.out.println(align(toPrint));
            System.out.println(line);
            input = sc.next();
        }
        System.out.println(line);
        System.out.println(align("Goodnight! MouMou will go back to sleep now."));
        System.out.println(line);
    }

    static String align(String s) { // to align
        int stringLength = s.length();
        int lineLength = 73;
        int left = (int) Math.ceil((lineLength - stringLength) / 2);
        int right = (int) Math.floor((lineLength - stringLength) / 2);
        String newString = "";
        newString = String.format("%" + left + "s", " ") + s + String.format("%" + right + "s", " ");
        return newString;
    }
}