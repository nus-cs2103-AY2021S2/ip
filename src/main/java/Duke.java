import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("   Hello There! I'm Duke, always here for you!");
        System.out.println("   How can I help you today?");

        Task[] arr = new Task[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {
            //System.out.println("input is: " + input + "\n");
            switch (input) {
                case "list":
                    System.out.println("___");
                    for (int i = 0; i < counter; i++) {
                        System.out.print("   " + (i + 1) + ". [");
                        if (arr[i].getState() == Task_State.DONE) {
                            System.out.print("X");
                        } else {
                            System.out.print(" ");
                        }
                        System.out.println("] " + arr[i].getInput());
                    }
                    System.out.println("___");
                    input = sc.next();
                    break;
                case "done":
                    int task_No = sc.nextInt();
                    arr[task_No - 1].doTask();
                    System.out.println("___\n   Nice! I've marked this task as done:");
                    System.out.println("      [X] " + arr[task_No - 1].getInput() + "\n___");
                    input = sc.next();
                    break;

                default:
                    String item = sc.nextLine();
                    System.out.println("___\n   added: " + input + " " + item + "\n___");
                    input = input.concat(" " + item);
                    Task newTask = new Task(input);
                    arr[counter] = newTask;
                    counter++;
                    input = sc.next();
                    break;
            }
        }

        System.out.println("___\n Bye! Hope to see you again! :)\n___\n");
    }
}
