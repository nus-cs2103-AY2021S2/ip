import java.io.*;
import java.util.Locale;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String logo = "           ___  _           ,_,\n"
                + "         |  _  | |   ___   / /\n"
                + "         | | | | |  / _ \\ =; ;=,\n"
                + "         | |_| | |_| |_| \\ | |\n"
                + "          \\___/\\___/---;_| |_|\n";

        String border = " --*---*---*---*---*---*---*---*---*---*--";
        System.out.println(logo + "\n" + border + "\n  Hey there, Olaf here!\n  What can I do for you?");

        while(true) {
            String command = bf.readLine();
            if(command.toLowerCase().equals("bye")){
                String out = "Aww hope to see you soon, goodbye!";
                System.out.println(border + "\n  " + out + "\n" + border);
                break;
            }
            System.out.println(border + "\n  " + command + "\n" + border);
        }
    }
}
