import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("     Heyyoo! I am Luna!\n     What can I echo for you today? :D\n" + logo);
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        while (!inp.equals("bye")) {
            System.out.println("     " + inp);
            inp = sc.nextLine();
        }
        System.out.println("     Byeee! It was wonderful to have you here!");
    }
}


