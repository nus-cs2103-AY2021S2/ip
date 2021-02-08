public class TaskAction {
    private Task relatedTask;
    private int relatedTaskNumber;
    private String keyword;
    private String actionType;

    public TaskAction() {}

    public TaskAction(String actionType){
        this.actionType = actionType;
        this.relatedTask = null;
    }

    public TaskAction(String keyword, String actionType){
        this.keyword = keyword;
        this.actionType = actionType;
        this.relatedTask = null;
    }

    public TaskAction(Task relatedTask, String actionType){
        this.relatedTask = relatedTask;
        this.actionType = actionType;
        this.keyword = null;
    }

    public TaskAction(int relatedTaskNumber, String actionType){
        this.relatedTask = null;
        this.actionType = actionType;
        this.relatedTaskNumber = relatedTaskNumber;
        this.keyword = null;
    }

    public Task getRelatedTask() {
        return this.relatedTask;
    }

    public String getActionType(){
        return this.actionType;
    }

    public int getRelatedTaskNumber() {
        return relatedTaskNumber;
    }

    public String getKeyword(){
        return this.keyword;
    }
}
