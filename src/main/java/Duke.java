import main.java.DukeException;
import main.java.ListManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        final String FILENAME = "dukedata.txt";
        final String FOLDERNAME = "data";
        final String PATH = FOLDERNAME + "/" + FILENAME;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        try{
            File folder = new File(FOLDERNAME);
            if (folder.mkdir()){
                System.out.println("Folder created: " + folder.getName());
            }else{
                // System.out.println("Folder already exists.");
            }
            File dataFile = new File(PATH);
            if (dataFile.createNewFile()) {
                System.out.println("File created: " + dataFile.getName());
            } else {
                // System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        ListManager listManager = new ListManager(PATH);
        try {
            listManager.restoreDataFromDataFile();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        System.out.println(listManager.welcomeLine());
        String userinput = scanner.nextLine();

        while ( !userinput.equals("bye")){
            try {
                String outputString = listManager.handleAllUserInput(userinput);
                System.out.println(outputString);
            }catch(DukeException e){
                System.out.println(e.getMessage());
            }

            userinput = scanner.nextLine();
        }
        System.out.println(listManager.goodbyeLine());
    }
}
