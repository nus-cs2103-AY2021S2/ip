import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Bob :D\n" + "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

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

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty.", e);
                }
            } else if (userInput.length() >= 5 && userInput.startsWith("event")) {
                try {
                    int taskIndex = userInput.indexOf("/at");
                    if (taskIndex != -1) {
                        String taskName = userInput.substring(6, taskIndex - 1);
                        String dateTime = userInput.substring(taskIndex + 4);
                        int index = dateTime.indexOf(" ");
                        String time = dateTime.substring(index - 1);
                        LocalDate dateFormatted = LocalDate.parse(dateTime.substring(0, index));
                        Event newEvent = new Event(taskName, dateFormatted, time);
                        taskList.add(newEvent);
                        System.out.println("Alright, I have added this new event.\n" +
                                newEvent + "\n" +
                                "There is a total of " + taskList.size() + " tasks now.");
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
                        int index = deadline.indexOf(" ");
                        String time = deadline.substring(index - 1);
                        LocalDate dateFormatted = LocalDate.parse(deadline.substring(0, index));
                        Deadline newDeadline = new Deadline(taskName, dateFormatted, time);
                        taskList.add(newDeadline);
                        System.out.println("Alright, I have added this new deadline.\n" +
                                newDeadline + "\n" +
                                "There is a total of " + taskList.size() + " tasks now.");
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

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Remember to specify which task you want to remove" +
                            "using a valid number", e);
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid format, please try again using numbers only.", e);
                }
            } else {
                System.out.println("Sorry, I have no idea what that means :( Please try again!");
            }
        }
    }
}
