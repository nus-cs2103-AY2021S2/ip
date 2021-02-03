package main.java.classes;

import java.util.Scanner;

public class Ui {
    String line = ":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)";

    public void startMessage() {
        System.out.println("yo im Duckie! quack quack");
        System.out.println("what can i do for ya ;)");
        System.out.println(line);
    }
    public void endMessage() {
        System.out.println(line);
        System.out.println("goodbye! c ya soon ;)");
        System.out.println(line);
    }

    public void customLine() {
        System.out.println(line);
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void findMessage() {
        System.out.println("found it! here's your task(s):");
    }

}
