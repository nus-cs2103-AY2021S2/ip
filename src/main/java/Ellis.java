import java.util.Scanner;

public class Ellis {

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("**********************");
                System.out.println("Bye, see you soon!");
                System.out.println("**********************");
                break;
            }
            System.out.println("**********************");
            System.out.println(input);
            System.out.println("**********************");
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
