import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        List<Task> store = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] parts = input.split(" ");
                System.out.println("    ____________________________________________________________");
                if (parts[0].equals("bye")) {
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("    ____________________________________________________________");
                    break;
                } else if (parts[0].equals("list")) {
                    System.out.println("     Here are the tasks in your list:");
                    int count = 1;
                    for (Task t : store) {
                        System.out.println("     " + count + "." + t);
                        count++;
                    }
                } else if (parts[0].equals("done")) {
                    if (parts.length == 1) {
                        System.out.println("     ☹ OOPS!!! The " +
                                "description of done cannot be empty.");
                        throw new InsufficientArgumentsException();
                    }
                    int taskDone = Integer.parseInt(parts[1]);
                    int count = 1;
                    for (Task t : store) {
                        if (count == taskDone) {
                            System.out.println("     Nice! I've marked this task as done:");
                            store.set(count - 1, store.get(count - 1).markAsDone());
                            System.out.println("     " + store.get(count - 1));
                        }
                        count++;
                    }
                } else if (parts[0].equals("delete")) {
                    if (parts.length == 1) {
                        System.out.println("     ☹ OOPS!!! The " +
                                "description of delete cannot be empty.");
                        throw new InsufficientArgumentsException();
                    }
                    int taskToDelete = Integer.parseInt(parts[1]);
                    System.out.println("     Noted. I've removed this task: ");
                    System.out.println("     " + store.get(taskToDelete - 1));
                    store.remove(taskToDelete - 1);
                    System.out.println("     Now you have " + store.size() + " tasks in the list.");
                } else if (parts[0].equals("todo")) {
                    if (parts.length == 1) {
                        System.out.println("     ☹ OOPS!!! The " +
                                "description of a todo cannot be empty.");
                        throw new InsufficientArgumentsException();
                    }
                    System.out.println("     Got it. I've added this task:");
                    String taskDescription = "";
                    boolean first = true;
                    for (int i = 1; i < parts.length; ++i) {
                        if (!first) {
                            taskDescription += " " + parts[i];
                        } else {
                            taskDescription += parts[i];
                        }
                        first = false;
                    }
                    Todo todo = new Todo(taskDescription);
                    store.add(todo);
                    System.out.println("     " + todo);
                    System.out.println("     Now you have " + store.size() + " tasks in the list.");
                } else if (parts[0].equals("deadline")) {
                    if (parts.length == 1) {
                        System.out.println("     ☹ OOPS!!! The " +
                                "description of a deadline cannot be empty.");
                        throw new InsufficientArgumentsException();
                    }
                    System.out.println("     Got it. I've added this task:");
                    boolean flag = false, first = true;
                    String dueDate = "";
                    for (String s : parts) {
                        if (flag) {
                            if (!first) {
                                dueDate += ' ' + s;
                            } else {
                                dueDate += s;
                            }
                            first = false;
                        }
                        if (s.equals("/by")) {
                            flag = true;
                        }
                    }
                    LocalDate due = LocalDate.parse(dueDate);
                    String taskDescription = "";
                    first = true;
                    for (int i = 1; i < parts.length; ++i) {
                        if (!parts[i].equals("/by")) {
                            if (!first) {
                                taskDescription += " " + parts[i];
                            } else {
                                taskDescription += parts[i];
                            }
                            first = false;
                        } else {
                            break;
                        }
                    }
                    Deadline deadline = new Deadline(taskDescription, due);
                    store.add(deadline);
                    System.out.println("     " + deadline);
                    System.out.println("     Now you have " + store.size() + " tasks in the list.");
                } else if (parts[0].equals("event")) {
                    if (parts.length == 1) {
                        System.out.println("     ☹ OOPS!!! The " +
                                "description of an event cannot be empty.");
                        throw new InsufficientArgumentsException();
                    }
                    System.out.println("     Got it. I've added this task:");
                    boolean flag = false, first = true;
                    String eventDate = "";
                    for (String s : parts) {
                        if (flag) {
                            if (!first) {
                                eventDate += ' ' + s;
                            } else {
                                eventDate += s;
                            }
                            first = false;
                        }
                        if (s.equals("/at")) {
                            flag = true;
                        }
                    }
                    LocalDate date = LocalDate.parse(eventDate);
                    String eventDescription = "";
                    first = true;
                    for (int i = 1; i < parts.length; ++i) {
                        if (!parts[i].equals("/at")) {
                            if (!first) {
                                eventDescription += " " + parts[i];
                            } else {
                                eventDescription += parts[i];
                            }
                            first = false;
                        } else {
                            break;
                        }
                    }
                    Event event = new Event(eventDescription, date);
                    store.add(event);
                    System.out.println("     " + event);
                    System.out.println("     Now you have " + store.size() + " tasks in the list.");
                } else {
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    throw new WrongArgumentException();
                }
                System.out.println("    ____________________________________________________________");
            } catch(WrongArgumentException e) {
                System.out.println("    ____________________________________________________________");
            } catch (InsufficientArgumentsException e) {
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
