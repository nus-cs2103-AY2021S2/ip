import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String partingLine = "_____________________________"
                + "_______________________________";

        System.out.println("Hello from\n" + logo);
        System.out.println(partingLine);
        System.out.println("Sup. I am Duke.");
        System.out.println("What do you want?");
        System.out.println(partingLine);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(partingLine);
            System.out.println(" " + input);
            System.out.println(partingLine);
            input = sc.nextLine();
        }
        System.out.println(partingLine);
        System.out.println(" Seeya.");
        System.out.println(partingLine);
    }
}
