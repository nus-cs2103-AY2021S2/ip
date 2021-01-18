import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        while(sc.hasNextLine()) {
            if (duke.parse(sc.nextLine()) == 0) break;
            System.out.println(duke.output());
        }
        System.out.println(duke.output());
    }
}
