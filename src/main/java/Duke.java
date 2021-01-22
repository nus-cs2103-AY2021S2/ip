import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private DataHandler storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new DataHandler(filePath);
        try {
            tasks = storage.loadData();
        } catch (FileNotFoundException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws DukeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            // try {
            Scanner sc = new Scanner(System.in);
            while (!isExit) {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, fullCommand, storage);
                isExit = c.isExit();
            }
//            } catch (DukeException e) {
//                e.getMessage();
//            } finally {
//                String line = "------------------------------------------";
//                System.out.println(line);
//            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Duke("data/tasks.txt").run();
    }
}
//        ArrayList<Task> list = DataHandler.loadData();
//
//        String line = "------------------------------------------";
//
//        Ui.showWelcome();
//
//        //start reading data from user
//        Scanner sc = new Scanner(System.in);
//        boolean flagger = true;
//
//        while (flagger) {
//            String input = sc.nextLine();
//            String action = input.split(" ")[0];
//            if (input.equals("bye")) {
//                System.out.println(line);
//                System.out.println("Bye. Hope to see you again soon!");
//                System.out.println(line);
//                flagger = false;
//
//            } else if (input.equals("list")) {
//                System.out.println(line);
//                System.out.println("Here are the tasks in your list:");
//                for (int i = 1; i < list.size() + 1; i++) {
//                        System.out.println(i + "." + list.get(i - 1));
//                }
//                System.out.println(line);
//
//            } else if (action.equals("done")) {
//                System.out.println(line);
//                int index = Integer.parseInt(input.split(" ")[1]) - 1;
//                System.out.println("Nice! I've marked this task as done:");
//                list.get(index).setDone();
//                System.out.println(list.get(index));
//                System.out.println(line);
//                DataHandler.saveData(list);
//
//            } else if (action.equals("todo")) {
//                String[] temp = input.split(" ", 2);
//                try {
//                    Task t = new Todo(temp[1]);
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    new EmptyToDoException();
//                    return;
//                }
//                Task t = new Todo(temp[1]);
//                System.out.println(line);
//                System.out.println("Got it. I've added this task:");
//                list.add(t);
//                System.out.println(t);
//                System.out.println("Now you have " + list.size() + " tasks in the list.");
//                System.out.println(line);
//                DataHandler.saveData(list);
//
//            } else if (action.equals("deadline")) {
//                String[] temp = input.split(" ", 2);
//                try {
//                    Task t = new Todo(temp[1]);
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    new EmptyDeadlineException();
//                    return;
//                }
//                String data = temp[1];
//                String description = data.split(" /by ", 2)[0];
//                String by = data.split(" /by ", 2)[1];
//
//                System.out.println(line);
//                System.out.println("Got it. I've added this task:");
//                Task t = new Deadline(description, by);
//                list.add(t);
//                System.out.println(t);
//                System.out.println("Now you have " + list.size() + " tasks in the list.");
//                System.out.println(line);
//                DataHandler.saveData(list);
//
//            } else if (action.equals("event")) {
//                String[] temp = input.split(" ", 2);
//                try {
//                    Task t = new Todo(temp[1]);
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    new EmptyEventException();
//                    return;
//                }
//                String data = temp[1];
//                String description = data.split(" /at ", 2)[0];
//                String at = data.split(" /at ", 2)[1];
//
//                System.out.println(line);
//                System.out.println("Got it. I've added this task:");
//                Task t = new Event(description, at);
//                list.add(t);
//                System.out.println(t);
//                System.out.println("Now you have " + list.size() + " tasks in the list.");
//                System.out.println(line);
//                DataHandler.saveData(list);
//
//            } else if (action.equals("delete")) {
//                String[] temp = input.split(" ", 2);
//
//                try {
//                    Task t = new Todo(temp[1]);
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    new EmptyDeleteException();
//                    return;
//                }
//
//                try {
//                    list.get(Integer.parseInt(temp[1]));
//                } catch (NumberFormatException e) {
//                    new AlphabetsInsteadOfNumberException();
//                    return;
//                }
//
//                try {
//                    list.get(Integer.parseInt(temp[1]));
//                } catch (IndexOutOfBoundsException e) {
//                    new EmptyListDeletionException();
//                    return;
//                }
//
//                System.out.println(line);
//                System.out.println("Noted. I've removed this task:");
//                int index = Integer.parseInt(temp[1]) - 1;
//                System.out.println(list.get(index));
//                list.remove(index);
//                System.out.println("Now you have " + list.size() + " tasks in the list.");
//                System.out.println(line);
//
//            } else {
//                new InvalidInstructionException();
//                return;
//            }
//        }
//    }
//}
