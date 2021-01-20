import java.lang.reflect.Array;
import java.util.*;


public class test{

    public static void main(String[] args) {

        //task 1
        //greeting line
        /*
        System.out.println("Hello my name is Duke\n" + "What can i do for you");
        Scanner sc = new Scanner(System.in); // takes in input
        while(sc.hasNext()){ //while there is an input
            String s = sc.next();// put the input into string
            if(s.equals("bye")){ //if the srting is end word
                System.out.println("Bye, hope to see you again soon"); //display terminating message
                break; //break the loop
            } else { //if the input is not the terminating word
                System.out.println(s); ///print out the input
            }
        }
        */

        //task 2
        //add list
        /*
        System.out.println("Hello! I am Duke\n" + "What can I do for you");
        Scanner sc = new Scanner(System.in);
        ArrayList xs = new ArrayList();
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            if(s.equals("bye")){
                System.out.println("Bye, hope to see you again soon!");
                break;
            } else if(!s.equals("list")){
                xs.add(s);
                System.out.println("added: " + s);
            } else {
                xs.stream().forEach(x->System.out.println(x));
            }
        }
        */

        System.out.println("Hello! I am Duke\n" + "What can I do for you");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> xsString = new ArrayList<>();
        ArrayList<Task>   xsTask   = new ArrayList<>();
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            Task t   = new Task(s);
            if(t.getDecs().equals("bye")){
                System.out.println("Bye, hope to see you again soon!");
                break;
            }
            else if( !t.getDecs().equals("list") && !t.getDecs().contains("done")){
                xsString.add(t.getDecs());
                xsTask.add(t);
                System.out.println("added: " + t.getDecs());
            } else if( t.getDecs().equals("list")){
                xsString.stream().forEach(x->System.out.println(t.getStatusIcon()+x));
            } else { //done
                int end = Integer.parseInt(t.getDecs().substring(t.getDecs().length()-1));
                Task a = xsTask.get(end-1);
                a.changeStatus();
                xsTask.remove(end);
                xsTask.add(end,a);
                //xsTask.get(end-1).changeStatus();
                System.out.println("Nice! I've marked this task as done");
                System.out.println(xsTask.get(end-1).getDecs() + xsTask.get(end-1).getStatusIcon());
            }
        }

    }

}