import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/*import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Timmy {

    private int noOfTasks = 0;
    private final ArrayList<Task> tasks;
    private boolean isExceptionCaught;

    public Timmy() {
        this.tasks = new ArrayList<>();
        this.isExceptionCaught = false;
    }


    Task addTask(String taskType, String taskInfo) throws InvalidDescriptionException{
        Task task = new Task();

        switch(taskType) {
        case "todo":
        {
            task = new ToDo(taskInfo);
            break;
        }
        case "deadline":
        {
            if (!(taskInfo.contains("/by"))) {
                throw new InvalidDescriptionException("");
            }
            String[] taskInfoArr = taskInfo.split(" /by ", 2);
            if (taskInfoArr.length < 2) {
                throw new InvalidDescriptionException("");
            }
            String[] dateAndTime = taskInfoArr[1].split(" ");
            String date = parseDate(dateAndTime[0]);
            String time = parseTime(dateAndTime[1]);
            String by = date + " " + time;
            task = new Deadline(taskInfoArr[0], by);
            break;
        }
        case "event":
        {
            if (!(taskInfo.contains("/at"))) {
                throw new InvalidDescriptionException("");
            }
            String[] taskInfoArr = taskInfo.split(" /at ", 2);
            if (taskInfoArr.length < 2) {
                throw new InvalidDescriptionException("");
            }
            String[] dateAndTime = taskInfoArr[1].split(" ");
            String date = parseDate(dateAndTime[0]);
            String time = parseTime(dateAndTime[1]);
            String by = date + " " + time;
            task = new Event(taskInfoArr[0], by);
            break;
        }
        default:
        {
            System.out.println("Invalid task!");
            break;
        }
        }

        tasks.add(noOfTasks, task);
        noOfTasks++;
        return task;
    }


    public String parseDate(String date) {
        LocalDate d1 = LocalDate.parse(date);
        System.out.println(d1);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String parseTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                .format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    void takeCommands() {
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            try {
                while (!(input = sc.nextLine()).equals("bye")) {
                    String[] tokens = input.split(" ", 2);
                    String taskType = tokens[0];



                    switch (taskType) {
                    case "list":
                    {
                        printList();
                        break;
                    }
                    case "done":
                    {
                        emptyDescriptionChecker(tokens);

                        String taskInfo = tokens[1];
                        int taskIndex = Integer.parseInt(taskInfo) - 1;
                        markTask(taskIndex);

                        System.out.println("------------------------------------------------");
                        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskIndex).toString());
                        System.out.println("------------------------------------------------");
                        break;
                    }
                    case "delete":
                    {
                        emptyDescriptionChecker(tokens);

                        String taskInfo = tokens[1];
                        int taskIndex = Integer.parseInt(taskInfo) - 1;
                        deleteTask(taskIndex);
                        break;
                    }
                    default:
                    {
                        emptyDescriptionChecker(tokens);

                        String taskInfo = tokens[1];
                        Task task = addTask(taskType, taskInfo);

                        System.out.println("------------------------------------------------");
                        System.out.println("Ok! I've added this task:\n" + task.toString());
                        System.out.println("Currently, you have " + noOfTasks + " task(s) in the list!");
                        System.out.println("------------------------------------------------");
                        break;
                    }
                    }
                    writeToFile(tasks);
                }
                isExceptionCaught = false;
            } catch (InvalidCommandException e) {
                System.out.println("------------------------------------------------");
                System.out.println("Sorry, I don't know what that means...");
                System.out.println("------------------------------------------------");

                isExceptionCaught = true;
            } catch (InvalidDescriptionException e) {
                System.out.println("------------------------------------------------");
                System.out.println("Sorry, I am unable to process what was written after the command...");
                System.out.println("------------------------------------------------");

                isExceptionCaught = true;
            } catch (EmptyDescriptionException e) {
                System.out.println("------------------------------------------------");
                System.out.println("Sorry, nothing was written after the command so I am unable to process...");
                System.out.println("------------------------------------------------");

                isExceptionCaught = true;
            } catch (Exception e) {
                System.out.println("------------------------------------------------");
                System.out.println("Sorry, I am unable to process");
                System.out.println("------------------------------------------------");

                isExceptionCaught = true;
            }
        } while (isExceptionCaught);
        sc.close();
    }
}*/
