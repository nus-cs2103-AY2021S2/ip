import java.util.*;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        List<String> ls = new ArrayList<>();
        List<Task> tsk = new ArrayList<>();

        String s = "";

        while (!(s = sc.nextLine()).equals("bye")) {

            String[] split = s.split(" ");

            if (split[0].equals("done")) {
                Task tk = tsk.get(Integer.parseInt(split[1]) - 1);
                tk.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + tk.getStatusIcon() + " " + tk.toString());

            } else if (split[0].equals("list")) {
                for (int i = 0; i < tsk.size() ; i++ ) {
                    System.out.println(i + 1 + ". " + tsk.get(i).getStatusIcon() + " " + tsk.get(i));
                }
            } else { //add task
                Task taskadd = new Task(s);
                ls.add(s);
                tsk.add(taskadd);
                System.out.println("added: " + taskadd.toString());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

