import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printErrorMessage(String message) {
        System.out.println("    ☹ OOPS!!! " + message);
    }

    // public static void deleteTask(int i) {
    // try {
    // Tasl task =
    // } catch (Exception e) {
    // //TODO: handle exception
    // }
    // }
    public static Task[] removeTheElement(Task[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }
        Task[] anotherArray = new Task[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            anotherArray[k++] = arr[i];
        }
        return anotherArray;
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    ____________________________________________________________";
        String terminate_input = "bye";
        String show_list = "list";
        String done = "done";
        String delete = "delete";
        String indentation = "    ";
        Scanner sc = new Scanner(System.in);
        System.out.println(line);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What I can do for you?");
        System.out.println(line);
        System.out.println();

        Task[] todo = new Task[100];
        int position = 0;

        while (true) {
            String input = sc.nextLine();
            System.out.println(line);
            if (input.equals(terminate_input)) {
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                break;
            } else if (input.equals(show_list)) {
                System.out.println("    Here are the tasks in your list: ");
                for (int i = 1; i < position + 1; i++) {
                    System.out.println(indentation + i + ". " + todo[i - 1]);
                }
            } else if (input.length() > 4 && input.substring(0, 4).equals(done)) {
                try {
                    if (input.length() <= 5) {
                        throw new DukeException("It seems you forget to tell me which task you have done.");
                    }
                    int job_done = Integer.valueOf(input.substring(5));
                    Task job = todo[job_done - 1];
                    job.markAsDone();
                    todo[job_done - 1] = job;
                    System.out.println(indentation + "Nice! I've marked this task as done:");
                    System.out.println(indentation + job);
                } catch (DukeException e) {
                    // TODO: handle exception
                    printErrorMessage(e.getMessage());
                }
            } else if (input.length() > 4 && input.substring(0, 6).equals(delete)) {
                try {
                    int index = Integer.valueOf(input.substring(7));
                    if (index > position + 1) {
                        throw new DukeException("Seems like you do not have so many tasks in your list");
                    }
                    Task temp = todo[index - 1];
                    todo = removeTheElement(todo, index - 1);
                    position -= 1;
                    System.out.println(indentation + "Noted, I've removed this task: ");
                    System.out.println(indentation + temp);
                    System.out.println(indentation + "Now you have " + position + " tasks in the list.");
                } catch (DukeException e) {
                    // TODO: handle exception
                    printErrorMessage(e.getMessage());
                }
            } else {
                try {
                    char caseType = input.charAt(0);
                    switch (caseType) {
                        case 'd':
                            if (input.length() <= 9) {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                            int i1 = input.indexOf("/");
                            String by = input.substring(i1 + 3);
                            String d1 = input.substring(8, i1 - 1);
                            Deadline ddl = new Deadline(d1, by);
                            todo[position] = ddl;
                            break;
                        case 'e':
                            if (input.length() <= 6) {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                            int i2 = input.indexOf("/");
                            String at = input.substring(i2 + 3);
                            String d2 = input.substring(5, i2 - 1);
                            Event event = new Event(d2, at);
                            todo[position] = event;
                            break;
                        case 't':
                            if (input.length() <= 5) {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                            String d3 = input.substring(4, input.length());
                            Todo t = new Todo(d3);
                            todo[position] = t;
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    int total = position + 1;
                    System.out.println(indentation + "Got it. I've added this task:");
                    System.out.println(indentation + todo[position]);
                    System.out.println(indentation + "Now you have " + total + " tasks in the list.");
                    position += 1;
                } catch (DukeException e) {
                    // TODO: handle exception
                    printErrorMessage(e.getMessage());
                }
            }
            System.out.println(line);
            System.out.println();
        }
        System.out.println(line);
    }
}
