
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.nio.*;

public class Duke {

    public static void PrintIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }
    public static void PrintToTxt(TaskList TLmain) throws IOException {
        //make the string
        String strOut = "";
        // type | 1-> done / 0-> not done | description | date & time
        for( int i = 0; i < TLmain.TaskArrList.size(); i++){
            //String stat = "";
            String str = "";
            if(TLmain.TaskArrList.get(i).getStatusIcon().equals(" ")){
                str = str + "0";
            }
            else{
                str = str + "1";
            }

            str = "| " + str + " | " + TLmain.TaskArrList.get(i).description;

            if(TLmain.TaskArrList.get(i) instanceof Deadlines){
                str = "D " + str + " | " + ((Deadlines) TLmain.TaskArrList.get(i)).by;
            }
            else if(TLmain.TaskArrList.get(i) instanceof Events){
                str = "E " + str + " | " + ((Events) TLmain.TaskArrList.get(i)).at;
            }
            else{
                str = "T " + str;
            }

            if(i != TLmain.TaskArrList.size() - 1){
                strOut = strOut + str + "\n";
            }
            else{
                strOut = strOut + str;
            }
        }
        //write to file
        //System.out.println("THIS IS BEING PRINTED INTO DATA.txt:");
        //System.out.println("tasklist size: " + TLmain.TaskArrList.size());
        //System.out.println(strOut);
        Path path = Paths.get("src/main/java/data.txt");
        try {
            Files.write(path, strOut.getBytes(StandardCharsets.UTF_8));
        }
        catch(IOException e){
            FileCreator(path);
            PrintToTxt(TLmain);
        }
    }

    public static void FileCreator(Path path){
        File newFile = new File(path.toString());
        try{
            newFile.createNewFile();
            System.out.println("File was created: " + path.toString());
        }
        catch(IOException e){
            System.out.println("Error File could not be created");
        }

    }




   public static void CommandHandler(TaskList TLmain){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            int prevStateChangeNum = TLmain.getStateChangeNum();
            String str = sc.nextLine();
           // System.out.println(str);
            if(str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }
            else if(str.equals("list")){
                try {
                    String s = TLmain.listTasks();
                    System.out.println(s);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
            else{
                if(str.contains("done")){
                    try {
                        TLmain.doneTask(str);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else if(str.contains("delete")){
                    try {
                        TLmain.deleteTask(str);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else {
                    //let TaskList determine type of Task to be added
                    try {
                        TLmain.addTask(str);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }

                try{
                    PrintToTxt(TLmain);
                }
                catch(IOException e){
                    System.out.println("ERROR WHEN PRINTING OUT");
                }
            }


        }

   }

   public static void FileLoad(TaskList TLmain){
       //check whether file exists
       File file = new File("src/main/java/data.txt");
       Path path = Paths.get("src/main/java/data.txt");
       if (file.isFile()){
            //check whether TLmain's TaskArrayList is empty (should be)
           if(TLmain.TaskArrList.isEmpty()){
               //execute parsing and loading of tasks into arraylist
               try {
                   Scanner sc = new Scanner(new File(path.toString()));
                   while(sc.hasNextLine()){
                       String fileline = sc.nextLine();

                       if (fileline.charAt(0) == 'T'){
                            Todo newTask = new Todo(fileline.substring(8));
                            if( fileline.charAt(4) == '1'){
                                newTask.markAsDone();
                            }
                            TLmain.TaskArrList.add(newTask);
                       }
                       else if(fileline.charAt(0) == 'D'){
                            //index of last "|"
                            int idx = fileline.lastIndexOf("|");
                            Deadlines newTask = new Deadlines(fileline.substring(8,idx - 1),
                                    fileline.substring(idx+2));
                           if( fileline.charAt(4) == '1'){
                               newTask.markAsDone();
                           }
                            TLmain.TaskArrList.add(newTask);
                       }
                       else if (fileline.charAt(0) == 'E'){
                           int idx = fileline.lastIndexOf("|");
                           Events newTask = new Events(fileline.substring(8,idx - 1),
                                   fileline.substring(idx+2));
                           if( fileline.charAt(4) == '1'){
                               newTask.markAsDone();
                           }
                           TLmain.TaskArrList.add(newTask);
                       }

                   }
               }
               catch (IOException e){
                   System.out.println("Error loading from text file");
                   e.printStackTrace();
               }

           }
       }
       else{
            FileCreator(path);
            FileLoad(TLmain);
       }
   }

    public static void main(String[] args) throws IOException{
        TaskList TLmain = new TaskList();
        PrintIntro();
        FileLoad(TLmain);
        CommandHandler(TLmain);
    }




}
