package level4.IO;

import level4.Task;

import java.io.*;
import java.util.*;

public class whatGoesOutside {
    static BufferedWriter server = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static final String initialDukeSymbol =
            "\n ____        _        \n"
            + "|  _ \\ _   _| | _____ \n" 
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
            
    static void wrongInputLah() { //in the event the input given is wrong format
        System.err.println("the input given does not match the format"); //display error message
    }

    public whatGoesOutside() { }
    
    void attachInNewLn(String string){ //to use the line symbol
        try{
            server.append(string);
            server.newLine(); //line symbol
        }catch (Exception exception) { //in event wrong format
            wrongInputLah(); //catch exception
        }
    }
    
    void putNewLineToAll(String... strings){ // append new line in
        Arrays.asList(strings).forEach(this::attachInNewLn); //use stream
    }
    
    public void addMessage(String message){ //to add the corresponding message
        try{
            server.append(message); //put in the message
        }catch (Exception e){
            wrongInputLah(); //catch exception when input given is wrong
        }
    }

    public void talk(){  //flush out
        try{
            server.flush();
        }catch (Exception e){ //if input is wrong
            wrongInputLah(); //catch the input if its wrong
        }
    }

    public void tellMeSomething(String something) { //writes the specified message requested
        try{
            this.attachInNewLn(something); //try to add the line
            server.flush();
        }catch (Exception e){ //if wronf input
            wrongInputLah(); //throw it
        }
    }

    public void tellMeEverything(String... messages){ //similat to prev method but writes multilple msg
        try{
            putNewLineToAll(messages); //adds the messages
            server.flush();
        }catch (Exception e){ //if the input is wrong
            wrongInputLah(); //cathc the error
        }
    }

    public void helloWorldTime(){ //intoductory msg
        this.tellMeSomething("The only verdict is vengeance; " +
                "a vendetta held as a votive, not in vain, for the value and veracity " +
                "of such shall one day vindicate the vigilant and the virtuous. Verily, " +
                "this vichyssoise of verbiage veers most verbose vis-à-vis an introduction, " +
                "and so it is my very good honor to meet you and you may call me Duke, " +
                 initialDukeSymbol + "How can I help?");
    }

    public void endingNote() {
        this.tellMeSomething("Farewell, adieu, goodbye"); //ending messages
    }

    public void notifyMeThatTaskWasAdded(Task task){ //tell us when the task is added into list
        this.tellMeEverything("This task has been added: ",
                task.toString()); //added msg
    }

    public void notifyMeThatTaskWasMarked(Task task){ //tell us when the task has been marked
        this.tellMeEverything("The task requested has now been marked as done:",
                task.toString()); //marked msg
    }

    public void endItAll() { //ending msg
        try{
            server.close(); //if all good, close it
        }catch (Exception exception){ //if input wrong
            wrongInputLah(); //catch the error
        }
    }
}