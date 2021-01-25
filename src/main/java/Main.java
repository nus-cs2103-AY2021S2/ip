import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        
        String input = "";
        String output = "";

        while(duke.isRunning()) {
            input = sc.nextLine();
            duke.getResponse(input);
        }

        sc.close();
    }
}
