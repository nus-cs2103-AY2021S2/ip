import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();

        System.out.println("\t_____________________________________________________________");
        System.out.println("\t Hello! I'm Duke\n\t What can I do for you");
        System.out.println("\t_____________________________________________________________");

        String textInput = read.nextLine();
        Boolean checkBye = false;
        if (textInput.toLowerCase().equals("bye")) {
            checkBye = true;
        }

        while (!checkBye) {
            if (textInput.toLowerCase().equals("list")) {
                int i = 1;
                System.out.println("\t_____________________________________________________________");
                System.out.println("\t Here are the tasks in your list:");
                for (Task task : listOfTasks) {
                    System.out.println("\t " + i + "." + task);
                    i++;
                }
                System.out.println("\t_____________________________________________________________");
            } else if (textInput.startsWith("done ")) {
                try {
                    int i = Integer.parseInt(textInput.substring(5));
                    Task task = listOfTasks.get(i - 1);
                    task.completed();
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t Nice! I've marked this task as done:");
                    System.out.println("\t   " + task);
                    System.out.println("\t_____________________________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("INVALID COMMAND!");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("INVALID COMMAND!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("INVALID COMMAND!");
                }
            } else if (textInput.startsWith("delete ")) {
                try {
                    int i = Integer.parseInt(textInput.substring(7));
                    Task task = listOfTasks.get(i - 1);
                    listOfTasks.remove(i - 1);
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t Noted. I've removed this task:");
                    System.out.println("\t   " + task);
                    System.out.println("\t Now you have " + listOfTasks.size() + " tasks in the list.");
                    System.out.println("\t_____________________________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("INVALID COMMAND!");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("INVALID COMMAND!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("INVALID COMMAND!");
                }
            } else if (textInput.startsWith("todo")) {
                try {
                    textInput = textInput.substring(5);
                    Task task = new ToDo(textInput);
                    listOfTasks.add(task);
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t Got it. I've added this task:");
                    System.out.println("\t   " + task);
                    System.out.println("\t Now you have " + listOfTasks.size() + " tasks in the list.");
                    System.out.println("\t_____________________________________________________________");
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("\t_____________________________________________________________");
                }
            } else if (textInput.startsWith("deadline")) {
                try {
                    textInput = textInput.substring(9);
                    int index = textInput.indexOf("/by ");
                    if (index != -1) {
                        String dateInfo = textInput.substring(index + 4);
                        LocalDate localDate = LocalDate.parse(dateInfo);
                        Task task = new Deadline(textInput.substring(0, index - 1), localDate);
                        listOfTasks.add(task);
                        System.out.println("\t_____________________________________________________________");
                        System.out.println("\t Got it. I've added this task:");
                        System.out.println("\t   " + task);
                        System.out.println("\t Now you have " + listOfTasks.size() + " tasks in the list.");
                        System.out.println("\t_____________________________________________________________");
                    } else {
                        System.out.println("INVALID COMMAND!");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("\t_____________________________________________________________");
                } catch (DateTimeParseException e) {
                    System.out.println("INVALID COMMAND!");
                }
            } else if (textInput.startsWith("event")) {
                try {
                    textInput = textInput.substring(6);
                    int index = textInput.indexOf("/from ");
                    if (index != -1) {
                        String dateInfo = textInput.substring(index + 6);
                        int index2 = dateInfo.indexOf("to ");
                        if (index2 != -1) {
                            LocalDate startDate = LocalDate.parse(dateInfo.substring(0, index2 - 1));
                            LocalDate endDate = LocalDate.parse(dateInfo.substring(index2 + 3));
                            Task task = new Event(textInput.substring(0, index - 1), startDate, endDate);
                            listOfTasks.add(task);
                            System.out.println("\t_____________________________________________________________");
                            System.out.println("\t Got it. I've added this task:");
                            System.out.println("\t   " + task);
                            System.out.println("\t Now you have " + listOfTasks.size() + " tasks in the list.");
                            System.out.println("\t_____________________________________________________________");
                        } else {
                            System.out.println("INVALID COMMAND!");
                        }
                    } else {
                        System.out.println("INVALID COMMAND!");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\t_____________________________________________________________");
                    System.out.println("\t ☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println("\t_____________________________________________________________");
                } catch (DateTimeParseException e) {
                    System.out.println("INVALID COMMAND!");
                }
            } else {
                System.out.println("\t_____________________________________________________________");
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t_____________________________________________________________");
            }

            textInput = read.nextLine();
            if (textInput.toLowerCase().equals("bye")) {
                checkBye = true;
            }
        }

        System.out.println("\t_____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t_____________________________________________________________");
    }
}
