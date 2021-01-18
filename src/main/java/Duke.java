import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hi, I am Bebong, a self-centered bot. I can't do anything for you.";
        String bye = "Goodbye, hope to not see you again.";
        List<Task> lst = new ArrayList<>();
//        System.out.println("Hello from\n" + logo);
        System.out.println(greetings);
        while (sc.hasNext()) {
            String word = sc.nextLine();
            String[] tmp = word.split(" ");
            if (word.equals("bye")) {
                System.out.println(bye);
                break;
            }
            else if (word.equals("list")) {
                for (int i=0; i < lst.size(); i++) {
                    System.out.print(i+1);
                    System.out.print(".");
                    System.out.println(lst.get(i));
                }
            }
            else if (tmp[0].equals("done")) {
                Task currTask = lst.get(Integer.parseInt(tmp[1]) - 1);
                currTask.doTask();
                System.out.println("Nice I have marked this task as done!");
                System.out.println(currTask);
            }
            else {
                Task task;
                if (tmp[0].equals("todo")) {
                    String realWord = word.substring(5);
                    task = new ToDo(realWord);
                    lst.add(task);
                }
                else if (tmp[0].equals("deadline")) {
                    String realWord = word.substring(9);
                    String[] deadlineWords = realWord.split("/by");
                    String deadlineWord = deadlineWords[0];
                    String deadlineTime = deadlineWords[1];
                    task = new Deadline(deadlineWord, deadlineTime);
                    lst.add(task);
                }
                else if (tmp[0].equals("event")){
                    String realWord = word.substring(6);
                    String[] eventWords = realWord.split("/at");
                    String eventWord = eventWords[0];
                    String eventTime = eventWords[1];
                    task = new Event(eventWord, eventTime);
                    lst.add(task);
                }
                else {
                    task = new Task("dummy");
                }
                System.out.println("Got it, I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + lst.size() + " tasks in the list.");
            }
        }
    }
}
