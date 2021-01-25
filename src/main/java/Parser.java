import java.io.IOException;
import java.util.Scanner;

public class Parser {
    static void parse() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exit_now = false;
        while (!exit_now) {
            String inp = sc.nextLine();
            String[] spl = inp.split(" ", 2);
            try {
                switch (spl[0]) {
                    case "todo":
                        check_spl_length(spl, 2, "todo");
                        TaskList.process_todo(spl);
                        break;
                    case "deadline":
                        check_spl_length(spl, 2, "deadline");
                        String[] spl2 = spl[1].split(" /by ", 2);
                        check_spl_length(spl2, 2, "deadline");
                        TaskList.process_deadline(spl2);
                        break;
                    case "event":
                        check_spl_length(spl, 2, "event");
                        String[] spl3 =spl[1].split(" /at ", 2);
                        check_spl_length(spl3, 2, "event");
                        TaskList.process_event(spl3);
                        break;
                    case "done":
                        check_spl_length(spl, 2, "done");
                        is_int(spl[1]);
                        TaskList.process_done(spl);
                        break;
                    case "list":
                        check_spl_length(spl, 1, "list");
                        TaskList.process_list();
                        break;
                    case "delete":
                        check_spl_length(spl, 2, "delete");
                        is_int(spl[1]);
                        TaskList.process_delete(spl);
                        break;
                    case "bye":
                        check_spl_length(spl, 1, "bye");
                        TaskList.process_bye();
                        exit_now = true;
                        break;
                    default:
                        throw new InvalidKeywordException();
                }
            } catch (InvalidTaskFormatException e) {
                e.printMessage();
            } catch (InvalidNumberException e) {
                e.printMessage();
            } catch (InvalidKeywordException e) {
                e.printMessage();
            }
        }
    }

    static void is_int(String s) throws InvalidNumberException {
        try {
            int x = Integer.parseInt(s);
            if (x > TaskList.getCount())
                throw new InvalidNumberException(Ui.InvalidNumberExceptionMessage());
        } catch (NumberFormatException e){
            throw new InvalidNumberException(Ui.InvalidNumberExceptionMessage());
        }
    }


    static void check_spl_length(String[] spl, int x, String task) throws InvalidTaskFormatException {
        if (spl.length != x)
            throw new InvalidTaskFormatException(Ui.InvalidTaskFormatExceptionMessage(task));
    }
}
