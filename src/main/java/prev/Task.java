package prev;
//prev here refers to codes for level 1,2,3

import java.util.*;
/*
note : u2713 is ascii for tick and u2718 is ascii for cross
 */

public class Task {
    //var declaration
    protected String decs;
    protected boolean isDone;

    //constructor
    public Task(String decs){
        this.decs   = decs;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDecs(){
        return this.decs;
    }
    public void changeStatus(){
        this.isDone = !(this.isDone || this.isDone);
    }

    public static void main(String[] args) {
        System.out.println("\u2713");
    }
}
