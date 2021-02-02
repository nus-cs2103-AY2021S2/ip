import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MyDuke {

    public static String DASH = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        print(new String[] {
            "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.",
            "Pai Kia Bot: What you want?"
        });

        System.out.print("You: ");
        String input = sc.nextLine();

        //level-1
        while (!input.equals("bye")) {

            //Level-2 implementation
            if (input.equals("list")) {
                int counter = 1;
                String[] tempArr = new String[100];
                for (String s : list) {
                    tempArr[counter-1] = counter + ". " + s;
                    counter++;
                }
                print(tempArr);
            } else {
                list.add(input);
                print("Paikia Bot: ok i just added: " + input);
            }
            
            
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
            //Level-2 adjustments
            if (s != null) {
                System.out.println(s);
            }
            
        }
        System.out.println(DASH);
    }

}
