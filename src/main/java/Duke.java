import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/*
Need to address:
- IO exception in process_bye
- making task an abstract class
- upload from hard drive default expression
- declared get_date abstract even though todo doesn't use it
- support different date formats and time also
 */

public class Duke {
    public static void main(String[] args) throws IOException {
        Ui.greet();
        TaskList.check_file_folder_specifications();
        Parser.parse();
    }
}
