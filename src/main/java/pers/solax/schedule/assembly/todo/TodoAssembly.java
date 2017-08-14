package pers.solax.schedule.assembly.todo;

import pers.solax.schedule.mapping.todo.TodoMapping;
import pers.solax.schedule.template.todo.TodoTemplate;

/**
 * Created by solax on 2017-4-22.
 */
public class TodoAssembly extends TodoAllAssembly {
    public TodoAssembly() {
        super(new TodoTemplate(), new TodoMapping());
    }
}
