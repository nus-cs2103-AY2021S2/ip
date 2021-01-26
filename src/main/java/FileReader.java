import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;

class FileReader {

    List<Task> readFile() throws DukeException{
        try {
            String path = "../tasks.txt";
            File f = new File(path);

            if(!f.exists()) {
                //create a new file and write as if the list is empty
                f.createNewFile();
                FileWriter fw = new FileWriter(path);
                fw.write("Done tasks: " + System.lineSeparator() + "Pending tasks: " + System.lineSeparator());
                fw.close();

                f = new File(path);
            }

            Scanner sc = new Scanner(f);
            List<Task> tasks = new ArrayList<>();
            boolean isDone = true;

            while(sc.hasNext()) {
                String currStr = sc.nextLine();

                if(currStr.equals("Done tasks: ") || currStr.equals("Pending tasks: ")) {

                    if(currStr.equals("Pending tasks: ")) {
                        isDone = false;
                    }

                    continue;
                }

                Task t = toTask(currStr);

                if(isDone) {
                    t.markAsDone();
                }

                tasks.add(t);

            }

            return tasks;

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    Task toTask(String input) throws DukeException{
        Scanner sc = new Scanner(input);
        String command = sc.next();

        String[] args = sc.nextLine().split("[|]");

        String first = args[0].trim();
        String second = null;

        if(args.length == 2) {
            second = args[1].trim();
        }

        switch(command) {
            case "todo":
                return new Todo(first);
            case "event":
                return new Event(first, second);
            case "deadline":
                return  new Deadline(first, second);
            default:
                return null;
        }
    }

}