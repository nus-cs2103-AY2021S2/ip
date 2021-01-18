import java.util.HashMap;

public enum Command {
    LIST, TODO, DEADLINE, EVENT, BYE;
    static{
        HashMap<String, Command> map = new HashMap<>();
        map.put("list",LIST);
        map.put("todo",TODO);
        map.put("deadline",DEADLINE);
        map.put("event",EVENT);
        map.put("bye",BYE);
    }
}
