import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) throws IOException {
        Duke chatbot = new Duke();
        chatbot.greet();

        while(true) {
            String string = br.readLine();
            if(!string.equals("bye")) {
                String[] starr = string.split(" ");
                if(string.equals("list")) {
                    chatbot.list.printList();
                } else if(starr[0].equals("done")){
                    chatbot.list.lst.get(Integer.parseInt(starr[1]) - 1).done();
                    pw.println("Nice! I've marked this task as done:");
                    pw.printf(" %s%n", chatbot.list.lst.get(Integer.parseInt(starr[1]) - 1));
                    pw.flush();
                } else if(starr[0].equals("todo")) {
                    String message = "";
                    String type = null;
                    for (int i = 0; i < starr.length; i++) {
                        if (i == 0) {
                            type = starr[i];
                        } else {
                            message = message.concat(starr[i]);
                            if (i != starr.length - 1) {
                                message = message + " ";
                            }
                        }
                    }
                    chatbot.addTask(message, type, null);
                } else if(starr[0].equals("deadline") || starr[0].equals("event")) {
                    String[] arr = string.split("/");
                    String date = arr[1];
                    String message = "";
                    String[] s = arr[0].split(" ");
                    String type = "";
                    for(int i = 0; i < s.length; i++) {
                        if(i == 0) {
                            type = s[i];
                        } else {
                            message = message.concat(s[i]);
                            if(i != s.length - 1) {
                                message = message + " ";
                            }
                        }
                    }
                    chatbot.addTask(message, type, date);
                }
            } else {
                chatbot.exit();
                break;
            }
        }
        pw.close();
    }
}
