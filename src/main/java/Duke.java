import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println("    ____________________________________");
        System.out.println("    Hello! I'm Duke \n    What can I do for you?");
        System.out.println("    ____________________________________");

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<Task>();
        int currIndex = 0;

        while (sc.hasNext()) {
            try {
                String command = sc.next();
                System.out.println("    ____________________________________");

                if (command.equals("bye")) {
                    System.out.println("    Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 1; i <= currIndex; i++) {
                        System.out.printf("    %d.%s\n", i, list.get(i - 1));
                    }
                } else if (command.equals("done")) {
                    // To get the index 
                    int index = sc.nextInt();
                    list.get(index - 1).markAsDone();

                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("        " + list.get(index - 1));
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    if (command.equals("todo")) {
                        String name = sc.nextLine();
                        if (!name.equals("")) {
                            list.add(new Todo(name));
                        } else {
                            throw new NoSuchElementException("    ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } else if (command.equals("deadline")) {
                        String name = sc.nextLine();
                        if (!name.equals("")) {
                            String[] split = name.split("/by", 2);
                            list.add(new Deadline(split[0], split[1]));
                        } else {
                            throw new NoSuchElementException("    ☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                    } else {
                        String name = sc.nextLine();
                        if (!name.equals("")) {
                            String[] split = name.split("/at", 2);
                            list.add(new Event(split[0], split[1]));
                        } else {
                            throw new NoSuchElementException("    ☹ OOPS!!! The description of an event cannot be empty.");
                        }
                    }
                
                    currIndex++;

                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + list.get(currIndex - 1));
                    System.out.printf("    Now you have %d tasks in the list.\n", currIndex);
                } else if (command.equals("delete")) {
                    // To get the index 
                    int index = sc.nextInt();
                    Task temp = list.get(index - 1);
                    list.remove(index - 1);
                    currIndex--;

                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("        " + temp);
                    System.out.printf("    Now you have %d tasks in the list.\n", currIndex);
                } else {
                    throw new UnknownCommandException();
                }

               
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("    ____________________________________");
            }
            
        }
        
        sc.close();
    }
}
