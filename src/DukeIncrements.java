import java.util.Scanner;

public class DukeIncrements {
    public static void main(String[] agrs) {
        System.out.println("yo im Duke!");
        System.out.println("what can i do for ya ;)");
        String line = ":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)";
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        int count = 0;
        Task[] arr = new Task[100];
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] str = input.split(" ");
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("sayonara nerd! c ya soon ;)");
                System.out.println(line);
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                System.out.println("get to work! these are the tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ".[" + arr[i].getStatus() + "] " + arr[i].description);
                }
                System.out.println(line);
            } else if (str[0].equalsIgnoreCase("done")) {
                System.out.println(line);
                int taskNum = Integer.parseInt(str[1]);
                Task temp = arr[taskNum - 1];
                temp.markDone();
                System.out.println("cool! this task is marked as done:");
                System.out.println("[" + temp.getStatus() + "] " + temp.description);
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
                arr[count] = new Task(input);
                count++;
                }
            }
        }
}

class Task {
    String description;
    boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatus() {
        return (isDone ? "\u2718" : " ");
    }

    public void markDone() {
        isDone = true;
    }
}