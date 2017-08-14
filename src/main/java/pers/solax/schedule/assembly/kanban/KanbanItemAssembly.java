package pers.solax.schedule.assembly.kanban;

import pers.solax.schedule.mapping.kanban.KanbanItemMapping;
import pers.solax.schedule.template.kanban.KanbanItemTemplate;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanItemAssembly extends KanbanAllAssembly {
    public KanbanItemAssembly () {
        super(new KanbanItemTemplate(), new KanbanItemMapping());
    }
}
