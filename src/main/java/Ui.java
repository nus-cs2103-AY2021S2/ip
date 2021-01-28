import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public void interactWithUser(ArrayList<Task> arrayList, String path) {
        String input;
        Scanner scanner = new Scanner(System.in);
        new Parser().makingSenseOfUserCommand(arrayList, path, scanner);
    }




}
