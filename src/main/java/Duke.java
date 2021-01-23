import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Duke {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        List<Task> list = new ArrayList<>();

        greet();
        commandList(list);
    }

    //Duke greets the user.
    public static void greet() {
        System.out.println("\t____________________________________________________________\n"
                        + "\tHello! I'm Duke\n\tWhat can I do for you?\n"
                        + "\t____________________________________________________________\n");
    }

    //Echos the input back to the user.
    public static void echo() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\t____________________________________________________________\n"
                    + "\t" + input + "\n"
                    + "\t____________________________________________________________\n");
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n"
        );
    }

    //Adds the input in a list and echo it back to the user.
    //Prints the list if input is "list"
    //Mark task as done if input is "done" with task number.
    public static void commandList(List<Task> list) {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                showList(list);
            } else {
                try {
                    String[] check = input.split(" ");
                    if (check[0].equals("done")) {
                        markTaskDone(list, check);
                    } else if (check[0].equals("delete")) {
                        deleteFromList(list, check);
                    } else {
                        addToList(list, input, check);
                    }
                } catch (TaskNotFoundException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tTask not in list.\n"
                                    + "\t____________________________________________________________\n");
                } catch (CommandNotValidException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tCommand not valid. Please use \"todo\", \"deadline\"\n"
                                    + "\tor \"event\" followed by task description to add new tasks.\n"
                                    + "\tPlease use \"list\" to view your list of tasks.\n"
                                    + "\tPlease use \"done\" followed by index to mark completed tasks.\n"
                                    + "\tPlease use \"delete\" followed by index to delete tasks.\n"
                                    + "\tPlease use \"bye\" to exit.\n"
                                    + "\t____________________________________________________________\n");
                } catch (DescriptionNotFoundException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease provide description for your task.\n"
                                    + "\t____________________________________________________________\n");
                } catch (DateTimeNotFoundException | StringIndexOutOfBoundsException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease enter the date (DD/MM/YYYY) with optional\n"
                                    + "\ttime (in 24 hours format) after \"/by\" for Deadline Tasks\n"
                                    + "\tor date with optional start and end time after \"/at\" \n"
                                    + "\tfor Event Tasks.\n"
                                    + "\t____________________________________________________________\n");
                } catch (InvalidTaskSelectionException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease enter task number after command.\n"
                                    + "\t____________________________________________________________\n");
                } catch (DateTimeParseException e) {
                    System.out.println("\t____________________________________________________________\n"
                            + "\tPlease enter in DD/MM/YYYY format (eg. 02/04/2000) for dates\n"
                            + "\tand in 24 hour format (eg. 1830) for times.\n"
                            + "\t____________________________________________________________\n");
                } catch (InvalidTimeDurationException e) {
                    System.out.println("\t____________________________________________________________\n"
                            + "\tPlease enter a valid start and end time duration\n\t(start time < end time).\n"
                            + "\t____________________________________________________________\n");
                }
            }
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }

    //Prints out the items in the list.
    public static void showList(List<Task> list) {
        if (list.size() == 0) {
            System.out.println("\t____________________________________________________________\n"
                        + "\tThere are no items in your list.\n"
                        + "\t____________________________________________________________\n");
        } else {
            System.out.println("\t____________________________________________________________\n"
                    + "\tHere are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
            }
            System.out.println("\t____________________________________________________________\n");
        }
    }

    //Check if string is number
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Mark numbered task in list as done.
    public static void markTaskDone(List<Task> list, String[] check)
            throws TaskNotFoundException, InvalidTaskSelectionException {
        if (check.length == 1 || !isNumber(check[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(check[1]);
        if (num > 0 && num <= list.size()) {
            list.get(num - 1).markAsDone();
            System.out.println("\t____________________________________________________________\n"
                    + "\tNice! I've marked this task as done:\n\t\t"
                    + list.get(num - 1).toString() + "\n"
                    + "\t____________________________________________________________\n");

        } else {
            throw new TaskNotFoundException();
        }
    }

    //Add different type of tasks in list.
    public static void addToList(List<Task> list, String input, String[] check)
            throws CommandNotValidException, DescriptionNotFoundException,
                DateTimeNotFoundException, DateTimeParseException,
                    StringIndexOutOfBoundsException, InvalidTimeDurationException {
        Task temp;
        String description;
        String dateTime;

        if (check[0].equals("todo") || check[0].equals("deadline") || check[0].equals("event")) {
            if (check.length == 1) {
                throw new DescriptionNotFoundException();
            }
            if (check[0].equals("todo")) {
                description = input.substring(5).trim();
                temp = new TodoTask(description);
            } else if (check[0].equals("deadline")) {
                int index = input.indexOf("/by");
                if (index == -1) {
                    throw new DateTimeNotFoundException();
                }

                description = input.substring(9, index).trim();
                if (description.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }

                dateTime = input.substring(index + 3).trim();
                if (dateTime.isEmpty()) {
                    throw new DateTimeNotFoundException();
                }

                String dateString = dateTime.substring(0, 10);
                LocalDate date = LocalDate.
                        parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String timeString = dateTime.substring(10).trim();
                if (timeString.isEmpty()) {
                    temp = new DeadlineTask(description, date);
                } else {
                    LocalTime time = LocalTime.
                            parse(dateTime.substring(10).trim(), DateTimeFormatter.ofPattern("HHmm"));
                    temp = new DeadlineTask(description, date, time);
                }
                //System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                //System.out.println(time.format(DateTimeFormatter.ofPattern("hh:mm a")));
            } else {
                int index = input.indexOf("/at");
                if (index == -1) {
                    throw new DateTimeNotFoundException();
                }

                description = input.substring(6, index).trim();
                if (description.isEmpty()) {
                    throw new DescriptionNotFoundException();
                }

                dateTime = input.substring(index + 3).trim();
                if (dateTime.isEmpty()) {
                    throw new DateTimeNotFoundException();
                }

                String dateString = dateTime.substring(0, 10);
                LocalDate date = LocalDate.
                        parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String timeString = dateTime.substring(10).trim();
                if (timeString.isEmpty()) {
                    temp = new EventTask(description, date);
                } else {
                    String startTimeString = timeString.substring(0, 4);
                    LocalTime startTime = LocalTime.
                            parse(startTimeString, DateTimeFormatter.ofPattern("HHmm"));
                    String endTimeString = timeString.substring(4).trim();
                    if (endTimeString.isEmpty()) {
                        temp = new EventTask(description, date, startTime);
                    } else {
                        LocalTime endTime = LocalTime.
                                parse(endTimeString, DateTimeFormatter.ofPattern("HHmm"));
                        if (endTime.compareTo(startTime) < 0) {
                            throw new InvalidTimeDurationException();
                        }
                        temp = new EventTask(description, date, startTime, endTime);
                    }
                }
            }
        } else {
            throw new CommandNotValidException();
        }
        list.add(temp);
        System.out.println("\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t   " + temp.toString() + "\n"
                + "\tNow you have " + list.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    //Delete task from list
    public static void deleteFromList(List<Task> list, String[] check)
            throws InvalidTaskSelectionException, TaskNotFoundException{
        if (check.length == 1 || !isNumber(check[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(check[1]);
        if (num > 0 && num <= list.size()) {
            Task deleted = list.get(num - 1);
            list.remove(num - 1);
            System.out.println("\t____________________________________________________________\n"
                    + "\tNoted. I've removed this task:\n"
                    + "\t   " + deleted.toString() + "\n"
                    + "\tNow you have " + list.size() + " tasks in the list.\n"
                    + "\t____________________________________________________________\n");

        } else {
            throw new TaskNotFoundException();
        }
    }
}
