import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Marvin and I will assist you with your tasks.\n"
                + "What can I do for you today?\n";
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        boolean isDone = false;
        List<String> taskList = new ArrayList<>();

        while (!isDone) {
            String task = in.nextLine();
            if (task.equals("bye"))
                isDone = true;
            else if (task.equals("list")) {
                IntStream.range(0, taskList.size())
                        .forEach(idx -> {
                            System.out.println(idx + 1 + ": " + taskList.get(idx));
                        });
            } else {
                System.out.println("added: " + task + "\n");
                taskList.add(task);
            }
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        in.close();
    }
}
