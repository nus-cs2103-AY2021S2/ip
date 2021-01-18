public class Tasks {
    private String[] list;
    private boolean[] done;
    private int index;

    public Tasks() {
        list = new String[101];
        done = new boolean[101];
        index = 1;
    }

    public void add(String s) {
        this.list[index] = s;
        index++;
    }

    public int index() {
        return this.index;
    }

    public void markDone(int i) {
        this.done[i] = true;
    }

    public String get(int i){
        return list[i];
    }

    public String checkDone(int i) {
        if (done[i]) {
            return i + ".[X] " + get(i);
        } else {
            return i + ".[ ] " + get(i);
        }
    }
}
