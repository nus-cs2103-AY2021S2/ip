import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hey yo, I'm Travis.\nI make you work. \n";
        String goodbye = "    Bye bye, catch you soon.";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        int numberOfTasks = 0;
        String input, description = "", time = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        String[] descriptionAndTime;
        LocalDate date = null;
        System.out.println(greeting);
        Command taskType = Command.NONE;

        while (!taskType.equals(Command.BYE)) {
            try {
                input = sc.nextLine();
                String[] taskInformation = input.split(" ", 2);
                String[] commands = input.split(" ");
                taskType = Command.valueOf(commands[0].toUpperCase());

                switch (taskType) {
                    case LIST:
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 1; i <= numberOfTasks; i++) {
                            System.out.println("    " + i + ". " + listOfTasks.get(i - 1).getStatus());
                        }
                        System.out.println();
                        break;

                    case DELETE:
                        int deleteIndex = Integer.parseInt(input.split(" ")[1]);
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("      " + listOfTasks.get(deleteIndex - 1).getStatus());
                        numberOfTasks--;
                        System.out.println("    Now you have " + numberOfTasks + " tasks in the list.\n");
                        listOfTasks.remove(deleteIndex - 1);
                        System.out.println();
                        break;
                    case DONE:
                        int taskNumber = Integer.parseInt(commands[1]);
                        listOfTasks.get(taskNumber - 1).setDone(true);
                        System.out.println("    Nice! I've marked this task as done: \n" + "      "
                                + listOfTasks.get(taskNumber - 1).getStatus());
                        System.out.println();
                        break;
                    case DEADLINE:
                    case EVENT:
                    case TODO:
                        if (taskInformation.length < 2) {
                            throw new TaskException(
                                    "    ☹ OOPS!!! The description of a " + taskInformation[0] + " cannot be empty.\n");
                        }
                        if (!taskType.equals(Command.TODO)) {
                            descriptionAndTime = taskInformation[1].split("/", 2);
                            if (descriptionAndTime.length < 2) {
                                throw new TaskException(
                                        "    ☹ OOPS!!! The time of a " + taskInformation[0] + " cannot be empty.\n");
                            }
                            description = descriptionAndTime[0];
                            time = descriptionAndTime[1].split(" ", 2)[1];
                            date = LocalDate.parse(time, formatter);
                        }

                        listOfTasks.add(taskType.equals(Command.EVENT) ? new Event(description, date)
                                : taskType.equals(Command.TODO) ? new ToDo(taskInformation[1])
                                        : new Deadlines(description, date));
                        System.out.println("    Got it. I've added this task: ");
                        System.out.println("      " + listOfTasks.get(numberOfTasks).getStatus());
                        numberOfTasks++;
                        System.out.println("    Now you have " + numberOfTasks + " tasks in the list.\n");
                        break;
                    case NONE:
                        throw new TaskException("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    case BYE:
                        break;

                }

            } catch (TaskException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }

            try {
                File savedTasks = new File("savedTasks.txt");
                savedTasks.createNewFile();
                FileWriter writer = new FileWriter("savedTasks.txt");
                for (int i = 0; i < numberOfTasks; i++) {
                    writer.write(listOfTasks.get(i).saveStatus());
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        sc.close();
        System.out.println(goodbye);

    }
}
