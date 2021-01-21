import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        
        String input = "";
        String output = "";

        duke.init();

        while(duke.isRunning()) {
            input = sc.nextLine();
            System.out.println("____________________________________________________________");
            output = duke.getResponse(input);
            System.out.println(output);
        }

        sc.close();
    }
}
