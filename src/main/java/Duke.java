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
        boolean isRun = true;
        List<Task> taskList = new ArrayList<>();

        while (isRun) {
            String command = in.next();
            String desc = in.nextLine();

            if (command.equals("bye"))
                isRun = false;
            else if (command.equals("list")) {
                IntStream.range(0, taskList.size())
                        .forEach(idx -> {
                            System.out.println(idx + 1 + "." + taskList.get(idx));
                        });
                System.out.println();
            } else if (command.equals("done")) {
                Integer idx = Integer.valueOf(desc.replaceAll("\\s", ""));
                taskList.get(idx - 1).setDone(true);
                System.out.println();
            } else {
                String task = command + desc;
                System.out.println("added: " + task + "\n");
                taskList.add(new Task(task));
            }
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        in.close();
    }
}
