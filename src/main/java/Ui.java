import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void runUi(TaskList taskList, Storage storage) {
        Scanner sc = new Scanner(System.in);
        boolean Done = false;
        while (!Done) {
            String nextCommand = sc.next();
            switch (nextCommand) {
            case "list":
                System.out.println("    ____________________________________________________________");
                for (int taskNum = 0; taskNum < taskList.taskList.size(); taskNum++) {
                    System.out.println("     " + (taskNum + 1) + "." + taskList.taskList.get(taskNum));
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
                taskList.taskList.add(nextToDo);
                System.out.println("    ____________________________________________________________\n" +
                        "     task.add:");
                System.out.println("       " + nextToDo + "\n" +
                        "     task.count = [" + taskList.taskList.size() + "].");
                System.out.println("    ____________________________________________________________");
                break;

            case "deadline":
                String[] deadLineArgs = sc.nextLine().split(" /by ");

                try {
                    Deadline nextDeadLine = new Deadline(deadLineArgs[0], deadLineArgs[1]);
                    taskList.taskList.add(nextDeadLine);
                    System.out.println("    ____________________________________________________________\n" +
                            "     task.add:");
                    System.out.println("       " + nextDeadLine + "\n" +
                            "     task.count = [" + taskList.taskList.size() + "].");
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
                    taskList.taskList.add(nextEvent);
                    System.out.println("    ____________________________________________________________\n" +
                            "     task.add:");
                    System.out.println("       " + nextEvent + "\n" +
                            "     task.count = [" + taskList.taskList.size() + "].");
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
                    Task targetTask = taskList.taskList.get(doneTarget - 1);
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
                    Task targetTask = taskList.taskList.get(removeTarget - 1);
                    System.out.println("       " + targetTask);
                    System.out.println("    ____________________________________________________________");

                    taskList.taskList.remove(removeTarget - 1);
                } catch (Exception e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Error: delete command format incorrect.");
                    System.out.println("    ____________________________________________________________");
                }
                break;

            case "bye":
                try {
                    storage.writeToStorage(taskList);
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
        sc.close();
    }
}
