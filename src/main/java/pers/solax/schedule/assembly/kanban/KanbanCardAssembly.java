package pers.solax.schedule.assembly.kanban;

import pers.solax.schedule.mapping.kanban.KanbanCardMapping;
import pers.solax.schedule.template.kanban.KanbanCardTemplate;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanCardAssembly extends  KanbanAllAssembly {
    public KanbanCardAssembly() {
        super(new KanbanCardTemplate(), new KanbanCardMapping());
    }
}
