package duke.customClass;

import java.util.Scanner;

public class Ui {

    public void greetingMessage() {
        String logo = "   ___     ___    _  _     ___     ___     _\n" +
                "  |   \\   /   \\  | \\| |   |_ _|   | __|   | |\n" +
                "  | |) |  | - |  | .` |    | |    | _|    | |__\n" +
                "  |___/   |_|_|  |_|\\_|   |___|   |___|   |____|\n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";
        System.out.println("Welcome back Max, I'm a Dumb Assistant " +
                "Naively Intended (to) Ease Labour\n" + logo +
                "____________________________________________________________\n" +
                "What can I do for you?\n" +
                "____________________________________________________________");
    }

    public void separatorLine() {
        System.out.println("____________________________________________________________");
    }

    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
