import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hey there! This is Kums");
        System.out.println("How can i help?");
        System.out.println("---------------------------------------");
        Task[] list = new Task[100];
        int listLength = 0;
        String command = br.readLine();
        String[] arr = command.split(" ");
        System.out.println("---------------------------------------");
        while (!command.equals("bye")) {
            if (arr[0].equals("done")) {
                Task tobeDone = list[Integer.parseInt(arr[1]) - 1];
                tobeDone.completed();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("    " + tobeDone);
            }  else if (!command.equals("list")) {
                Task task = new Task(command);
                list[listLength++] = task;
                System.out.println("added: " + command);
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0;  i < listLength; i++) {
                    System.out.println( (i+1) + "."  + list[i]);
                }
            }
            System.out.println("---------------------------------------");
            command = br.readLine();
            arr = command.split(" ");
            System.out.println("---------------------------------------");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------");
    }
}
