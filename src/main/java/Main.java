import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chatbot owen = new Owen();
        Response response = owen.getResponse();
        System.out.println(response);

        Scanner scanner = new Scanner(System.in);

        while (owen.isRunning()) {
            String command = scanner.nextLine();
            owen = owen.parseCommand(command);
            System.out.println(owen.getResponse().getFormattedResponse());
        }

        scanner.close();
    }
}
