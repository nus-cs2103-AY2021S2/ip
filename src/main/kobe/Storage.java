package kobe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;

import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Storage {
//    private Path path;
    private String pathName;
    private TaskList tasks;
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";

    Storage(String pathName, TaskList tasks, Ui ui) {
//        this.path = path;
        this.pathName = pathName;

        //Read line by line of the storage file
        try (BufferedReader br = new BufferedReader(
                new FileReader(pathName, StandardCharsets.US_ASCII))) {

            boolean isFileEmpty = true;
            String readLine = br.readLine();

            while (readLine != null) {
                isFileEmpty = false;
                System.out.println(readLine);

                tasks.addItemByString(readLine); //PARSER //Actaully add the task //Parser
                readLine = br.readLine();
            }

            if (!isFileEmpty) {
                System.out.println(line + "Here are tasks that Kobe has retrieved!\n" + line);
            } else {
                ui.showLoadingError();
            }

        } catch (IOException e) {
            //do nothing
        } finally {
            this.tasks = tasks;
        }
    }


    //UPDATE THE KOBE.TXT FILE
    public void saveFile() {
//        java.nio.file.Path path = java.nio.file.Paths.get("home/ip/src/main/data/kobe.txt");
//        java.nio.file.Path path = java.nio.file.Paths.get(home + "/ip/src/main/data");
//        boolean directoryExists = java.nio.file.Files.exists(path);
//        System.out.println("Directory Home: " + home);
//        System.out.println("Directory: " + path.toString());
//        System.out.println("Directory exists?: " + directoryExists);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.pathName,
                StandardCharsets.US_ASCII))) {

            for (int i = 0; i < this.tasks.size(); i++) {
                bw.write(ind + (i + 1) + ". " + this.tasks.get(i) + "\n");
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

}