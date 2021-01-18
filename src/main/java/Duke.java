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
                Task task = new Task(word);
                lst.add(task);
                System.out.println("added: " + word);
            }
        }
    }
}
