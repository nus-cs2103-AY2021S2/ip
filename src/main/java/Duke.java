import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {

        ArrayList<Task> taskList = new ArrayList<>();

        File tasksFile = new File("duke.txt");
        if (!tasksFile.exists()) {
            tasksFile.createNewFile();
        }

        Scanner readFile = new Scanner(tasksFile);
        while (readFile.hasNext()) {
            String taskName = "";
            String nextTask = readFile.nextLine();
            boolean done = false;
            if (nextTask.charAt(4) == '1') {
                done = true;
            }
            if (nextTask.startsWith("T")) {
                taskName = nextTask.substring(8);
                taskList.add(new Todo(taskName, done));
            } else if (nextTask.startsWith("E")) {
                int index = nextTask.lastIndexOf("|");
                taskName = nextTask.substring(8, index - 1);
                String time = nextTask.substring(index + 2);
                taskList.add(new Event(taskName, done, time));
            } else if (nextTask.startsWith("D")) {
                int index = nextTask.lastIndexOf("|");
                taskName = nextTask.substring(8, index - 1);
                String deadline = nextTask.substring(index + 2);
                taskList.add(new Deadline(taskName, done, deadline));
            }
        }


        System.out.println("Hello! I'm Bob :D\n" + "What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        boolean updates = false;

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye! See you soon!");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    int index = i + 1;
                    System.out.println("This is your list of tasks:\n"+
                            index + "." + taskList.get(i));
                }
            } else if (userInput.length() >= 4 && userInput.startsWith("done")) {
                try {
                    String indexString = userInput.substring(5);
                    int index = Integer.parseInt(indexString);
                    Task updatedTask = taskList.get(index - 1);
                    updatedTask.status(true);
                    System.out.println("Good job! This task has been marked as done :)\n" + updatedTask);
                    updates = true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Remember to specify which task you are done with " +
                            "using a valid number", e);
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid format, please try again using numbers only.", e);
                }
            } else if (userInput.length() >= 4 && userInput.startsWith("todo")) {
                try {
                    String taskName = userInput.substring(5);
                    Todo newTodo = new Todo(taskName);
                    taskList.add(newTodo);
                    System.out.println("Alright, I have added this new todo.\n" +
                            newTodo + "\n" +
                            "There are a total of " + taskList.size() + " tasks now.");
                    updates = true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty.", e);
                }
            } else if (userInput.length() >= 5 && userInput.startsWith("event")) {
                try {
                    int taskIndex = userInput.indexOf("/at");
                    if (taskIndex != -1) {
                        String taskName = userInput.substring(6, taskIndex - 1);
                        String time = userInput.substring(taskIndex + 4);
                        Event newEvent = new Event(taskName, time);
                        taskList.add(newEvent);
                        System.out.println("Alright, I have added this new event.\n" +
                                newEvent + "\n" +
                                "There is a total of " + taskList.size() + " tasks now.");
                        updates = true;
                    } else {
                        System.out.println("There is no event timing detected!\n" +
                                "Please try again with a correct format");
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of an event cannot be empty.", e);
                }
            } else if (userInput.length() >= 8 && userInput.startsWith("deadline")) {
                try {
                    int deadlineIndex = userInput.indexOf("/by");
                    if (deadlineIndex != -1) {
                        String taskName = userInput.substring(9, deadlineIndex - 1);
                        String deadline = userInput.substring(deadlineIndex + 4);
                        Deadline newDeadline = new Deadline(taskName, deadline);
                        taskList.add(newDeadline);
                        System.out.println("Alright, I have added this new deadline.\n" +
                                newDeadline + "\n" +
                                "There is a total of " + taskList.size() + " tasks now.");
                        updates = true;
                    } else {
                        System.out.println("There is no deadline time and date detected!\n" +
                                "Please try again with a correct format");
                    }

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of a deadline cannot be empty.", e);
                }
            } else if (userInput.length() >= 6 && userInput.startsWith("delete")) {
                try {
                    String indexString = userInput.substring(7);
                    int index = Integer.parseInt(indexString);
                    Task deletedTask = taskList.remove(index - 1);
                    System.out.println("Alright, this task has been removed.\n" +
                            deletedTask + "\nThere are " + taskList.size() + " tasks left.");
                    updates = true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Remember to specify which task you want to remove" +
                            "using a valid number", e);
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid format, please try again using numbers only.", e);
                }
            } else {
                System.out.println("Sorry, I have no idea what that means :( Please try again!");
            }
            if (updates) {
                FileWriter fw = new FileWriter("duke.txt");
                for (int i = 0; i < taskList.size(); i++) {
                    Task nextTask = taskList.get(i);
                    String done = nextTask.done ? "1" : "0";
                    String type = nextTask.toString().substring(1,2);
                    String name = nextTask.name;
                    String time = "";
                    if (type.equals("E")) {
                        int index = nextTask.toString().indexOf("at: ");
                        time = nextTask.toString().substring(index + 4, nextTask.toString().length() - 1);
                    } else if (type.equals("D")) {
                        int index = nextTask.toString().indexOf("by: ");
                        time = nextTask.toString().substring(index + 4, nextTask.toString().length() - 1);
                    }
                    fw.write(type + " | " + done + " | " + name + time + System.lineSeparator());
                }
                fw.close();
                updates = false;
            }
        }
    }
}
