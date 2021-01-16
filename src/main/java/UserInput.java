import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    private Scanner sc;
    private List<Task> newStorage;

    public UserInput(Scanner sc) {
        this.newStorage = new ArrayList<>();
        this.sc = sc;
    }

    public void executeInput() {
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                indentInput("Bye. Hope to see you again!");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < this.newStorage.size(); i++) {
                    if (this.newStorage.get(i).getDone()) {
                        System.out.println(i + 1 + ".[X] " + this.newStorage.get(i).getDescription());
                    } else {
                        System.out.println(i + 1 + ".[ ] " + this.newStorage.get(i).getDescription());
                    }
                }
            } else if (userInput.length() == 6 && userInput.substring(0,4).equals("done") && Integer.valueOf(userInput.substring(5,6)) > 0 && Integer.valueOf(userInput.substring(5,6)) <= this.newStorage.size() + 1) {
                System.out.println("Nice! I've added this task as done");
                int selectedIndex = Integer.valueOf(userInput.substring(5,6)) - 1;
                this.newStorage.get(selectedIndex).setDone(true);
                System.out.println(selectedIndex + 1 + ".[X] " + this.newStorage.get(selectedIndex).getDescription());
            } else {
                indentInput(userInput);
                Task task = new Task(userInput);
                newStorage.add(task);
            }
        }
    }

    public static void indentInput(String input) {
        String line = "";
        for (int i = 0; i < 50; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
        System.out.println("added: " + input);
        System.out.println(line);
    }
}
