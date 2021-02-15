package kobe;

import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Ui {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";
    public static ArrayList<String> responses = new ArrayList<>();;

    /**
     * Constructor for the Ui object.
     */
    public Ui() {
//        System.out.println(line + "Hello! I'm Kobe\n" + ind + "What can I do for you?\n" + line);
    }

    /**
     * To print a line with indentation.
     */
    void showLine() {
        System.out.println(line);
    }

    /**
     * To print a line with indentation.
     *
     * @return  a line with indentation
     */
    String line() {
        return line;
    }

    /**
     * To print a line with indentation.
     *
     * @return  a line with indentation
     */
    String ind() {
        return ind;
    }

    /**
     * To get the most recent response for Kobe, in order to reply
     * to the user's most recent input.
     *
     * @return  Kobe's response
     */
    public static String getMostRecentResponse(){
        return responses.get(responses.size() - 1);

    }

    public static void addIncompleteDescriptionResponse() {
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like you have yet to complete your request.\n" +
                "Type help for more info");
    }

    public static void addIncorrectDescriptionResponse(){
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like your command is incorrect.\n" +
                "Type help for more info");
    }

    public static void addWhitespaceResponse(){
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like your command has too much empty spacing.\n" +
                "Type help for more info");
    }



    /**
     * To display an error message in the context of Kobe
     */
    void showLoadingError() {
        //"No prior saved data!"
        System.out.println(line + "Kobe detected no prior saved data!\n" + line);
    }

}