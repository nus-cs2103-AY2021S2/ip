import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ellis {
    List<String> lst = new ArrayList<>();

    public void iterateList() {
        int i = 1;
        for (String item : lst) {
            System.out.println(i + ". " + item);
            i++;
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("**********************");
                System.out.println("Bye, see you soon!");
                System.out.println("**********************");
                break;
            } else if (input.equals("list")) {
                System.out.println("**********************");
                iterateList();
                System.out.println("**********************");
            } else {
                lst.add(input);
                System.out.println("**********************");
                System.out.println("Added: " + input);
                System.out.println("**********************");
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        String logo = ".------..------..------..------..------.\n"
                + "|E.--. ||L.--. ||L.--. ||I.--. ||S.--. |\n"
                + "| (\\/) || :/\\: || :/\\: || (\\/) || :/\\: |\n"
                + "| :\\/: || (__) || (__) || :\\/: || :\\/: |\n"
                + "| '--'E|| '--'L|| '--'L|| '--'I|| '--'S|\n"
                + "`------'`------'`------'`------'`------'\n";

        System.out.println("Hello, this is \n" + logo);
        Ellis e = new Ellis();
        e.run();
    }
}
