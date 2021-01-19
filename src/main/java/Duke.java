import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> strArr = new ArrayList<>();

    public static void lines() {
        System.out.println("__________________________" +
                "__________________________________");
    }

    public static void greet() {
        String greetingCat = " ฅ(=^・ω・^=ฅ)";
        System.out.println(greetingCat);
        System.out.println("Mew! I'm Chat the Cat");
        System.out.println("What can I do for you?");
    }

    public static void echo(String s) {
        System.out.println(s);
    }

    public static void goodbye() {
        String goodByeCat = "（=^・ω・^=❁）";
        System.out.println("✧･ﾟ:* Goodbye *:･ﾟ✧" + goodByeCat);
    }

    public static void add(String s) {
        strArr.add(s);
        String goCat = "(^・ω・^=)~~✧✧";
        System.out.println(goCat);
        System.out.println("Added: " + s);
    }

    public static void list() {
        int i = 0;
        System.out.println(" ฅ list ฅ ");
        while (i < strArr.size()) {
            System.out.println(Integer.toString(i + 1) + ": " + strArr.get(i));
            i++;
        }
    }

    public static void main(String[] args) {
        //only add lines here
        lines();
        greet();
        lines();

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            lines();
            if (str.equals("list")) {
                list();
            } else {
                add(str);
            }
            lines();
            str = sc.nextLine();
        }

        lines();
        goodbye();
        lines();
    }
}
