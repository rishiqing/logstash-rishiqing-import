/**
 * Created by solax on 2017-5-7.
 */
public enum TestEntry {
    kanban,
    todo,
    kanbanAll;
    private String type = null;

    public void exec () {
        switch (this) {
            case kanban:
                System.out.println(type);
                System.out.println(this.name());
                break;
            case todo:
                System.out.println(type);
                System.out.println(this.name());
                break;
            case kanbanAll:
                System.out.println(type);
                System.out.println(this.name());
                break;
        }
    }
}
