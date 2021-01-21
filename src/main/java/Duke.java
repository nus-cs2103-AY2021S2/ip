import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeList list = new DukeList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String bound = "____________________________________________________________";
        String topBound = "____________________________________________________________\n";
        String bottomBound = "\n____________________________________________________________\n";

        System.out.println(topBound + logo + "\nHello! I'm Duke\n" + "What can I do for you?\n" + bottomBound);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] arr = input.split("/");
            if (input.equals("bye")) {
                System.out.println(topBound + "Good bye! Stay calm and keep coding o7" + bottomBound);
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println(bound);
                System.out.println("Here are the tasks in your list");
                list.printAll();
                System.out.println(topBound);
            } else if (arr[0].startsWith("done")) {
                String[] doneArr = arr[0].split(" ");
                int x = Integer.parseInt(doneArr[1]) - 1;
                list.done(x);
                System.out.println(topBound + "Good job! I've marked this task as done:\n" + "  " + list.get(x)
                        + bottomBound);
            } else if (arr[0].startsWith("todo")) {
                String taskName = input.replaceFirst("todo", "").stripLeading();
                ToDos curr = new ToDos(taskName);
                list.add(curr);
                System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                        + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
            } else if (arr[0].startsWith("deadline")) {
                String taskName = arr[0].replaceFirst("deadline", "").stripLeading().stripTrailing();
                Deadlines curr = new Deadlines(taskName, arr[1].replaceFirst("by", "").stripLeading());
                list.add(curr);
                System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                        + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
            } else if (arr[0].startsWith("event")) {
                String taskName = arr[0].replaceFirst("event", "").stripLeading().stripTrailing();
                Events curr = new Events(taskName, arr[1].replaceFirst("at", "").stripLeading());
                list.add(curr);
                System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                        + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
            } else {
                Task curr = new Task(input);
                list.add(curr);
                System.out.println(topBound + "added: " + input + bottomBound);
            }
        }
    }
}
