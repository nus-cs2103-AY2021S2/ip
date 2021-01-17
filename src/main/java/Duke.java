import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Task[] storage = new Task[100];
        int count = 0;

        System.out.println("     Heyyoo! I am Luna!\n     What can I echo for you today? :D\n" + logo);
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();

        while (!inp.equals("bye")) {
            String[] spl = inp.split(" ", 2);

            if (inp.equals("list")) {
                for (int i = 0; i < count; i++) {
                    int next = i + 1;
                    System.out.println("     " + next + ". " + storage[i]);
                }
            } else if (spl[0].equals("done") && is_int(spl[1])) {
                int number = Integer.parseInt(spl[1]);
                Task current = storage[number-1];
                current.finished();
                storage[number-1] = current;
                System.out.println("     Good job! Another Task completed! I have marked it as done:\n     " + current);
            } else {
                storage[count] = new Task(inp);
                count++;
                System.out.println("     Done adding: " + inp);
            }
            inp = sc.nextLine();
        }
        System.out.println("     Byeee! It was wonderful to have you here!");
    }

    static boolean is_int(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}


