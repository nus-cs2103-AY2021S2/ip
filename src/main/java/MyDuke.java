import java.util.Scanner;

public class MyDuke {

    public static String DASH = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        print(new String[] {
            "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.",
            "Pai Kia Bot: What you want?"
        });

        System.out.print("You: ");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            print("Paikia Bot: " + input);
            System.out.print("You: ");
            input = sc.nextLine();
        }

        print("Pai Kia Bot: Leave so soon ah? Limpeh sleep first, if got no issue don't disturb me.");


    }

    static void print(String s) {
        System.out.println(DASH);
        System.out.println(s);
        System.out.println(DASH);
    }

    static void print(String[] sArr) {
        System.out.println(DASH);
        for (String s : sArr) {
            System.out.println(s);
        }
        System.out.println(DASH);
    }

}
