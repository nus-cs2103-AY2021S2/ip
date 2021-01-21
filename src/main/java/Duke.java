import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello from Gambit, how may I assist you today?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while(sc.hasNext()) {
            String word = sc.nextLine();
            if (word.equals("bye")) {
                System.out.println("Bye, hope to see you again!");
                break;
            } else if (word.equals("list")) {
                int size = list.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= size; i++) {
                    System.out.println(i + ". " + list.get(i - 1).toString());
                }
            } else if (word.contains("done")) {
                String strArray[] = word.split(" ");
                int value =Integer.parseInt(strArray[1]);
                list.get(value - 1).markAsDone();
            } else {
                Task t = new Task(word);
                list.add(t);
                System.out.println("added: " + word);
            }
        }
    }
}
