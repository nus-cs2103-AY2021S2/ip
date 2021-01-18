import java.util.*;

class Statement {
    private final String statement;
    private final Hashtable<String, Integer> argsTable;

    Statement(String statement) {
        this.statement = statement;
        argsTable = new Hashtable<>();

        argsTable.put("list", 0);
        argsTable.put("done", 1);
        argsTable.put("todo", 1);
        argsTable.put("deadline", 2);
        argsTable.put("event", 2);
    }

    //return a list of String with the first element as the command
    //the second element is description and third element is time (if applicable)
    List<String> parseStatement() {
        Scanner sc = new Scanner(statement);
        String command = sc.next();

        List<String> result = new ArrayList<>();
        result.add(command);

        if (!argsTable.containsKey(command))  //unknown command
            throw new RuntimeException(command);

        int numOfArgs = argsTable.get(command);

        //there are no more arguments
        if(numOfArgs == 0) return result;

        String rest = sc.nextLine();
        String[] args = rest.split("[/]");

        //missing arguments
        if (args.length != numOfArgs)
            throw new NoSuchElementException("Too less arguments");



        for(int i = 0; i < numOfArgs; i++){
            result.add(args[i]);
        }

        return result;
    }
}
