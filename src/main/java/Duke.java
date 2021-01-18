import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("Hi! I'm Timmy!\nWhat can Timmy do for you today?");
        System.out.println("------------------------------------------------");
        String input;
        while(!(input = sc.nextLine()).equals("bye")){
            System.out.println("------------------------------------------------");
            System.out.println(input);
            System.out.println("------------------------------------------------");
        };
        sc.close();
        System.out.println("Bye! Hope to see you again!");
    }
}
