import java.util.Scanner;
import java.util.ArrayList;

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
                for (Task task : listOfTasks) {
                    System.out.println("\t " + i + "." + task);
                    i++;
                }
                System.out.println("\t_____________________________________________________________");
            } else if (textInput.contains("done")) {
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
            } else {
                listOfTasks.add(new Task(textInput));
                System.out.println("\t_____________________________________________________________");
                System.out.println("\t added: " + textInput);
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
