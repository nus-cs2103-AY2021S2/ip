import java.util.List;
import java.util.List;
import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    String logo =
            " __        _        \n"
                    + "|  _ \\ _   | | __ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| || | || |   <  __/\n"
                    + "|_/ \\,||\\\\___|\n";
    public void sendWelcomeMessage() {
        System.out.println("Hi Im Duke, how may I help you?");
    }

    public void byeUser() {
        System.out.println("Bye bye, have a nice day! Thanks for using " + logo);
    }

    public String listenToInput() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("loading failed");
    }
    public void showUiForAdd(Task task,int numOfItems) {
        System.out.print("added: ");
        System.out.println(task);
        System.out.println("Now you have " + numOfItems + " tasks in the list");
    }
    public void showUiForDelete(Task removedTask,int numOfItems) {
        System.out.println("Noted.I have removed this task:");
        System.out.print("  ");
        System.out.println(removedTask);
        System.out.println("Now you have " + numOfItems + " tasks in the list");
    }
    public void showUiForDone(Task task) {
        System.out.println("Nice, I have marked this task as done!");
        System.out.print("  ");
        System.out.print(task);
    }
    public void printList(List<Task> tasks) {
        System.out.println("here are your tasks:");
        for(int itemNo=1;itemNo<=tasks.size();itemNo++) {
            System.out.print("  ");
            System.out.println("" + itemNo + ". " + tasks.get(itemNo-1));
        }
    }
}

