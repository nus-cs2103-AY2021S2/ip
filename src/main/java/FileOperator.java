import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;

class FileOperator {

    List<String> readFile() throws DukeException{
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
            List<String> tasks = new ArrayList<>();

            while(sc.hasNext()) {
                String currStr = sc.nextLine();

                if(!currStr.equals("Done tasks: ") && !currStr.equals("Pending Tasks: ")) {
                    tasks.add(currStr);
                }
            }

            return tasks;

        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

}
