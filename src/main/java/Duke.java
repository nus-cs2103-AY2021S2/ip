import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________\n" +
                "     Duke... booted...\n" +
                "     requesting tasks\n" +
                "    ____________________________________________________________\n");

        //boolean to keep program running
        boolean Done = false;

        //data structure to store tasks
        ArrayList<Task> taskList = new ArrayList<>();

        //try to open past data and read to task list
        File dukeDataFile = new File("data/duke.txt");
        if (!dukeDataFile.exists()) {
            try {
                if (!dukeDataFile.createNewFile()) {
                    System.out.println("File not created.");
                }
            } catch (IOException e) {
                System.out.println("IOException caught.");
            }
        }

        try {
            Scanner fileScanner = new Scanner(dukeDataFile);
            while (fileScanner.hasNext()) {
                switch (fileScanner.next()) {
                case "todo":
                    String[] todoArgs = fileScanner.nextLine().split(" ");
                    Task newToDo = new ToDo(todoArgs[0]);
                    if (todoArgs[1].equals("done")) {
                        newToDo.MarkAsDone();
                    }
                    taskList.add(newToDo);
                    break;

                case "deadline":
                    String[] deadlineArgs = fileScanner.nextLine().split(" ");
                    Task newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                    if (deadlineArgs[2].equals("done")) {
                        newDeadline.MarkAsDone();
                    }
                    taskList.add(newDeadline);
                    break;

                case "event":
                    String[] eventArgs = fileScanner.nextLine().split(" ");
                    Task newEvent = new Event(eventArgs[0], eventArgs[1]);
                    if (eventArgs[2].equals("done")) {
                        newEvent.MarkAsDone();
                    }
                    taskList.add(newEvent);
                    break;

                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        //body of the program that reads user input
        while (!Done) {
            String nextCommand = sc.next();
            switch (nextCommand) {
            case "list":
                System.out.println("    ____________________________________________________________");
                for (int taskNum = 0; taskNum < taskList.size(); taskNum++) {
                    System.out.println("     " + (taskNum + 1) + "." + taskList.get(taskNum));
                }
                System.out.println("    ____________________________________________________________");
                break;

            case "todo":
                String scanTest = sc.nextLine();
                if (scanTest.strip().equals("")) {
                    System.out.println("    ____________________________________________________________\n" +
                            "     Error: ToDo entry format incorrect.\n" +
                            "    ____________________________________________________________");
                    break;
                }
                ToDo nextToDo = new ToDo(scanTest);
                taskList.add(nextToDo);
                System.out.println("    ____________________________________________________________\n" +
                        "     task.add:");
                System.out.println("       " + nextToDo + "\n" +
                        "     task.count = [" + taskList.size() + "].");
                System.out.println("    ____________________________________________________________");
                break;

            case "deadline":
                String[] deadLineArgs = sc.nextLine().split(" /by ");

                try {
                    Deadline nextDeadLine = new Deadline(deadLineArgs[0], deadLineArgs[1]);
                    taskList.add(nextDeadLine);
                    System.out.println("    ____________________________________________________________\n" +
                            "     task.add:");
                    System.out.println("       " + nextDeadLine + "\n" +
                            "     task.count = [" + taskList.size() + "].");
                    System.out.println("    ____________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Error: Deadline entry format incorrect.");
                    System.out.println("    ____________________________________________________________");
                }
                break;

            case "event":
                String[] eventArgs = sc.nextLine().split(" /at ");

                try {
                    Event nextEvent = new Event(eventArgs[0], eventArgs[1]);
                    taskList.add(nextEvent);
                    System.out.println("    ____________________________________________________________\n" +
                            "     task.add:");
                    System.out.println("       " + nextEvent + "\n" +
                            "     task.count = [" + taskList.size() + "].");
                    System.out.println("    ____________________________________________________________");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Error: Event entry format incorrect.");
                    System.out.println("    ____________________________________________________________");
                }
                break;

            case "done":
                try {
//                  int doneTarget = Integer.parseInt(sc.nextLine().split("\n")[0]);
                    int doneTarget = sc.nextInt();
                    System.out.println("    ____________________________________________________________\n" +
                            "     task.done = true:");
                    Task targetTask = taskList.get(doneTarget - 1);
                    targetTask.MarkAsDone();
                    System.out.println("       " + targetTask);
                    System.out.println("    ____________________________________________________________");

                } catch (Exception e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Error: done command format incorrect.");
                    System.out.println("    ____________________________________________________________");
                }
                break;

            case "delete":
                try {
                    int removeTarget = sc.nextInt();
                    System.out.println("    ____________________________________________________________\n" +
                            "     task.remove :");
                    Task targetTask = taskList.get(removeTarget - 1);
                    System.out.println("       " + targetTask);
                    System.out.println("    ____________________________________________________________");

                    taskList.remove(removeTarget - 1);
                } catch (Exception e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Error: delete command format incorrect.");
                    System.out.println("    ____________________________________________________________");
                }
                break;

            case "bye":
                try {
                    writeToFile(dukeDataFile, taskList);
                } catch (IOException e) {
                    System.out.println("IOException thrown, task list not saved.");
                }
                System.out.println("    ____________________________________________________________\n " +
                        "     goodBye.\n" +
                        "    ____________________________________________________________");
                Done = true;
                break;

            default:
                System.out.println("    ____________________________________________________________");
                System.out.println("     invalidCommand.");
                System.out.println("    ____________________________________________________________");
                break;
            }
        }
    }

    private static void writeToFile(File file, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        tasks.forEach(task -> {
            try {
                fw.write(task.generateDataString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("IOException thrown.");
            }
        });
    }
}
