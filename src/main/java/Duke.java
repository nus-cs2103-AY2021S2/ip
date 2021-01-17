import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String[] storage = new String[100];
        int count = 0;

        System.out.println("     Heyyoo! I am Luna!\n     What can I echo for you today? :D\n" + logo);
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();

        while (!inp.equals("bye")) {
            if (inp.equals("list")) {
                for (int i = 0; i < count; i++) {
                    int next = i + 1;
                    System.out.println("     " + next + ". " + storage[i]);
                }
            } else {
                storage[count] = inp;
                count++;
                System.out.println("     Done adding: " + inp);
            }
            inp = sc.nextLine();
        }
        System.out.println("     Byeee! It was wonderful to have you here!");
    }
}


