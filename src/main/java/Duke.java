import java.util.Scanner;

public class Duke {
    public static void addLine() {
        System.out.println("    ----------------------------------");
    }

    public static void main(String[] args) {
        Task[] inputArr = new Task[100];
        Scanner sc = new Scanner(System.in);
        addLine();
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        addLine();
        System.out.println();
        int n = 0;

        while(true) {
            String input = sc.nextLine();
            String[] markDone = new String[2];
            markDone = input.split(" ");

            switch(markDone[0]) {
                case "bye":
                    break;
                case "list":
                    addLine();
                    for(int i = 1; i <= n; i++) {
                        Task curr = inputArr[i - 1];
                        System.out.println("    " + i + ". " + "[" + curr.getStatusIcon() + "] " + curr.getDescription());
                    }
                    addLine();
                    System.out.println();
                    break;
                case "done":
                    int taskNum = Integer.parseInt(markDone[1]);
                    Task curr = inputArr[taskNum - 1];
                    addLine();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + "[" + curr.getStatusIcon() + "] " + curr.getDescription());
                    addLine();
                    System.out.println();
                    curr.toggleStatus();
                    break;
                default:
                    addLine();
                    System.out.println("    added: " + input);
                    addLine();
                    System.out.println();
                    inputArr[n] = new Task(input);
                    n++;
                    break;
            }
            if(input.equals("bye")) break;
        }
        addLine();
        System.out.println("    Bye. Hope to see you again soon!");
        addLine();
        System.out.println();
    }
}