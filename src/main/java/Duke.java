import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
            } else if (userInput.length() > 4 && userInput.startsWith("done ")) {
                try {
                    int index = Integer.parseInt(userInput.substring(5));
                    if (index < taskList.size()) {
                        Task updatedTask = taskList.get(index - 1);
                        updatedTask.status(true);
                        System.out.println("Good job! This task has been marked as done :)\n" +
                                updatedTask);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid format, please try again using numbers only.");
                }

            } else if (userInput.length() > 4 && userInput.startsWith("todo ")) {
                try {
                    String taskName = userInput.substring(5);
                    Todo newTodo = new Todo(taskName);
                    taskList.add(newTodo);
                    System.out.println("Alright, I have added this new todo.\n" +
                            newTodo + "\n" +
                            "There are a total of " + taskList.size() + " tasks now.");

                } catch (Exception e) {
                    System.out.println("Please indicate the new todo in the correct format.");

                }
            } else if (userInput.length() > 5 && userInput.startsWith("event ")) {
                try {
                    int taskIndex = userInput.indexOf("/at");
                    String taskName = userInput.substring(6, taskIndex - 1);
                    String time = userInput.substring(taskIndex + 4);
                    Event newEvent = new Event(taskName, time);
                    taskList.add(newEvent);
                    System.out.println("Alright, I have added this new event.\n" +
                            newEvent + "\n" +
                            "There is a total of " + taskList.size() + " tasks now.");

                } catch (Exception e) {
                    System.out.println("Please indicate the new event in the correct format.");
                }
            } else if (userInput.length() > 8 && userInput.startsWith("deadline ")) {
                try {
                    int deadlineIndex = userInput.indexOf("/by");
                    String taskName = userInput.substring(9, deadlineIndex - 1);
                    String deadline = userInput.substring(deadlineIndex + 4);
                    Deadline newDeadline = new Deadline(taskName, deadline);
                    taskList.add(newDeadline);
                    System.out.println("Alright, I have added this new deadline.\n" +
                            newDeadline + "\n" +
                            "There is a total of " + taskList.size() + " tasks now.");

                } catch (Exception e) {
                    System.out.println("Please indicate the new deadline in the correct format.");
                }
            } else {
                System.out.println("Please try again with the correct format.");
            }
        }
    }
}
