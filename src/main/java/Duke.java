import java.util.Scanner;

public class Duke {

    public static void lines() {
        System.out.println("__________________________" +
                "__________________________________");
    }

    public static void greet() {
        lines();
        String greetingCat = " ฅ(=^・ω・^=ฅ)";
        System.out.println(greetingCat);
        System.out.println("Mew! I'm Chat the Cat");
        System.out.println("What can I do for you?");
        lines();
    }

    public static void echo(String s) {
        lines();
        System.out.println(s);
        lines();
    }

    public static void goodbye() {
        lines();
        String goodByeCat = "（=^・ω・^=❁）";
        System.out.println("✧･ﾟ:* Goodbye *:･ﾟ✧" + goodByeCat);
        lines();
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        while (!str.equals("bye")) {
            echo(str);
            str = sc.next();
        }
        goodbye();
    }
}
