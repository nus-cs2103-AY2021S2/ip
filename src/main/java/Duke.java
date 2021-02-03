import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static String logo;
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        logo = " __        _        \n"
                + "|  _ \\ _   | | __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| || | || |   <  __/\n"
                + "|_/ \\,||\\\\___|\n";
        greetUser();
        while(true) {
            String cmd = sc.nextLine();
            if(cmd.equals("bye")) {
                byeUser();
                break;
            }
            else if(cmd.equals("list")) {
                listItems();
            }
            else {
                addItems(cmd);
            }
        }
    }

    private static void addItems(String cmd) {
        tasks.add(new Task(cmd));
        System.out.println("added: " + tasks.get(tasks.size()-1));
    }

    private static void listItems() {
        System.out.println("here are your tasks:");
        for(int itemNo=1;itemNo<=tasks.size();itemNo++) {
            System.out.print("  ");
            System.out.println("" + itemNo + ". " + tasks.get(itemNo-1));
        }
    }
    private static void greetUser() {
        System.out.println("Hello from\n" + logo);
    }
    private static void byeUser() {
        System.out.println("Bye from\n" + logo);
    }
    private static void echo() {
        while(true) {
            String cmd = sc.nextLine();
            if(cmd.equals("bye")) break;
            System.out.println(cmd);
        }
        byeUser();
    }
}
