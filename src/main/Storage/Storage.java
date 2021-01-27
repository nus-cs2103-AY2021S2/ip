package Storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileReader;

public class Storage {
    private Path path;
    private TaskList tasks;

    Storage(Path path, TaskList tasks) {
        this.path = path;

        //Read line by line of the storage file
        try (BufferedReader br = new BufferedReader(
                new FileReader(path, StandardCharsets.US_ASCII))) {

            boolean isFileEmpty = true;
            String readLine = br.readLine();

            while (readLine != null) {
                isFileEmpty = false;
                System.out.println(readLine);

                tasks.addItemByString(readLine) //PARSER //Actaully add the task //Parser
                readLine = br.readLine();
            }

            if (!isFileEmpty) {
                System.out.println(line + "Here are tasks that Kobe has retrieved!\n" + line);
            } else {
                Ui.showLoadingError();
            }

        } catch (IOException e) {
            //do nothing
        } finally {
            this.tasks = tasks;
        }
    }


    //UPDATE THE KOBE.TXT FILE
    private void saveFile() {
//        java.nio.file.Path path = java.nio.file.Paths.get("home/ip/src/main/data/kobe.txt");
//        java.nio.file.Path path = java.nio.file.Paths.get(home + "/ip/src/main/data");
//        boolean directoryExists = java.nio.file.Files.exists(path);
//        System.out.println("Directory Home: " + home);
//        System.out.println("Directory: " + path.toString());
//        System.out.println("Directory exists?: " + directoryExists);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path,
                StandardCharsets.US_ASCII))) {

            for(int i = 0; i < this.tasks.size(); i++) {
                bw.write(ind + (i+1) + ". " + this.tasks.get(i) + "\n");
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

}