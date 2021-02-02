import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MyDuke {

    public static String DASH = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>(); // change from string to task
        print(new String[] { "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.", "Pai Kia Bot: What you want?" });

        System.out.print("You: ");
        String input = sc.nextLine();

        // level-1
        while (!input.equals("bye")) {

            String[] inputArr = input.split(" ");

            // Level-2 implementation
            if (input.equals("list")) {
                int counter = 1;
                String[] tempArr = new String[100];
                for (Task t : list) { // changed String s to Task t
                    tempArr[counter - 1] = counter + ". " + t.toString();
                    counter++;
                }
                print(tempArr);
            } else if (inputArr[0].equals("done")) { // level-3 addition
                if (inputArr.length == 1 || inputArr.length > 2) { // if only done or got more than one arg
                    print("Paikia Bot: you done what task, limpeh need more information ah. input 'done <task number>. eg, done 3");
                } else {
                    int ref = Integer.parseInt(inputArr[1]); //error management: input ref may exceed total number of task in list, KIV
                    list.set(ref - 1, list.get(ref - 1).setAsDone());
                    print("Paikia Bot: ok i just help u checked this task as done -- " + list.get(ref - 1).toString());
                }
            } else {
                list.add(new Task(input, false));
                print("Paikia Bot: ok i just added: " + input);
            }

            System.out.print("You: ");
            input = sc.nextLine();
        }
        print("Pai Kia Bot: Leave so soon ah? Limpeh sleep first, if got no issue don't disturb me.");
    }

    static void print(String s) {
        System.out.println(DASH);
        System.out.println(s);
        System.out.println(DASH);
    }

    static void print(String[] sArr) {
        System.out.println(DASH);
        for (String s : sArr) {
            // Level-2 adjustments
            if (s != null) {
                System.out.println(s);
            }

        }
        System.out.println(DASH);
    }

}

// Level-3 additions
class Task {
    String info;
    boolean isDone;

    Task(String s, boolean b) {
        this.info = s;
        this.isDone = b;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    Task setAsDone() {
        return new Task(this.info, true);
    }

    Task setAsUndone() {
        return new Task(this.info, false);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.info;
    }
}