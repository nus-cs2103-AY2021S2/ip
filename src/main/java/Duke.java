import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  " _____   _   _ \n"
                + "| ____| | | | | \n"
                + "| |___  | | | | __   __ \n"
                + "|  ___| | | | | \\ \\ / / \n"
                + "| |___  | | | |  \\ v / \n"
                + "|_____| |_| |_|  /  / \n"
                + "                /__/  \n";

        System.out.println("   C H A T   W I T H \n" + logo);

        String tab = "     ";
        String line = "     ............................................................";
        System.out.println(line + "\n"
                + tab + "Hi there! I'm Elly.\n"
                + tab + "How can I help you today?\n"
                + line);

        Scanner scan = new Scanner(System.in);
        String[] data = new String[100];
        int index = 0;

        while (scan.hasNextLine()) {
            String input = scan.nextLine();

            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println(tab + "Goodbye, can't wait to see you again!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    int num = i + 1;
                    System.out.println(tab + num + ". " + data[i]);
                }
            } else {
                data[index] = input;
                index++;
                System.out.println(tab + "added: " + input);
            }
            System.out.println(line);
        }
    }
}
