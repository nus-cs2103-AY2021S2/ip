import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UI {
    private Parser parser;

    public UI() {
        parser = new Parser();
    }
    public void launchUI(TaskList listOfTasks) {
        Scanner read = new Scanner(System.in);

        System.out.println("\t_____________________________________________________________");
        System.out.println("\t Hello! I'm Duke\n\t What can I do for you");
        System.out.println("\t_____________________________________________________________");

        String textInput = read.nextLine();
        Boolean checkBye = false;
        if (textInput.toLowerCase().equals("bye")) {
            checkBye = true;
        }

        while (!checkBye) {
            parser.executeCommand(listOfTasks, textInput);
            textInput = read.nextLine();
            if (textInput.toLowerCase().equals("bye")) {
                checkBye = true;
            }
        }

        System.out.println("\t_____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t_____________________________________________________________");
    }
}
