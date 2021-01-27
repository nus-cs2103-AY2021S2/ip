import java.util.Scanner;

public class Parser {
    private final TaskList list;
    private final Ui ui;
    private final Scanner sc;

    public Parser(TaskList list, Ui ui) {
        this.list = list;
        this.ui = ui;
        this.sc = new Scanner(System.in);
    }

    public void poll() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list.listAll();
            } else if (input.contains("done")){
                int number;
                try {
                    number = (Integer.parseInt(input.substring(5))) - 1;
                    list.markDone(number);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printErr("No task was input to be marked as done.");
                } catch (IndexOutOfBoundsException e) {
                    ui.printErr("No task has been stored at that index yet.");
                } catch (NumberFormatException e) {
                    ui.printErr("Input index was not recognised.");
                }
            } else if (input.contains("delete")) {
                int number;
                try {
                    number = (Integer.parseInt(input.substring(7))) - 1;
                    list.delete(number);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.printErr("No task was input to be deleted.");
                } catch (IndexOutOfBoundsException e) {
                    ui.printErr("No task has been stored at that index yet.");
                } catch (NumberFormatException e) {
                    ui.printErr("Input index was not recognised.");
                }
            } else if (input.contains("todo")) {
                String task = input.substring(4);
                if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
                    ui.printErr("The description of a todo cannot be empty.");
                } else {
                    list.add(new Task(task, 0));
                }
            } else if (input.contains("deadline")) {
                String task = input.substring(8);
                if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
                    ui.printErr("The description of a deadline cannot be empty.");
                } else {
                    try {
                        list.add(new Task(task, 1));
                    } catch (IllegalArgumentException e) {
                        ui.printErr("The description or date of a deadline cannot be empty.");
                    }
                }
            } else if (input.contains("event")) {
                String task = input.substring(5);
                if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
                    ui.printErr("The description of an event cannot be empty.");
                } else {
                    try {
                        list.add(new Task(task, 2));
                    } catch (IllegalArgumentException e) {
                        ui.printErr("The description or date of an event cannot be empty.");
                    }
                }
            } else {
                ui.print("I'm sorry, but I don't know what that means :-(");
            }
            input = sc.nextLine();
        }
    }
}