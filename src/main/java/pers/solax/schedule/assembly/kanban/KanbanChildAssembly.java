package pers.solax.schedule.assembly.kanban;

import pers.solax.schedule.mapping.kanban.KanbanCardMapping;
import pers.solax.schedule.mapping.kanban.KanbanChildMapping;
import pers.solax.schedule.template.kanban.KanbanChildTemplate;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanChildAssembly  extends KanbanAllAssembly {
    public KanbanChildAssembly() {
        super(new KanbanChildTemplate(), new KanbanChildMapping());
    }
}
