import java.util.List;
import java.util.ArrayList;

class Storage {
    private final List<String> list;

    Storage(){
        list = new ArrayList<>();
    }

    void add(String item){
        list.add(item);
    }

    void listOut(){
        int index = 1;
        for(String s: list){
            System.out.println(index + ". " + s);
            index++;
        }
    }
}
