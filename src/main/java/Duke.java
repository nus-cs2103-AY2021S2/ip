import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  Duke {
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int numitems = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye")) {
            Pattern p = Pattern.compile("(done )([0-9]+)");
            Matcher m = p.matcher(str);
            if (str.equals("list")) {
                System.out.println("Here are the tasks in your list!");
                for (int i = 0; i < numitems; i++) {
                    System.out.println(i+1 + "." + list[i].getStatusIcon() + list[i].description);
                }

            } else if (m.find()) {
                    System.out.println("Good job, I've marked the task as done!");
                    int n = Integer.parseInt(m.group(2)) - 1;
                    list[n].markAsDone();
                    System.out.println(list[n].getStatusIcon() + list[n].description);
            } else {
                System.out.println("added: " + str);
                list[numitems] = new Task(str);
                numitems += 1;
            }
            str = sc.nextLine();
        }
        System.out.println("Bye friend, see you soon!");
    }
}
