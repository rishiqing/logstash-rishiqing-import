package pers.solax.schedule.assembly.kanban;

import pers.solax.Base.factory.IndexFactory;
import pers.solax.Base.schedule.MappingBase;
import pers.solax.Base.schedule.TemplateBase;
import pers.solax.schedule.mapping.kanban.KanbanMapping;
import pers.solax.schedule.template.kanban.KanbanTemplate;

/**
 * Created by solax on 2017-3-30.
 */
public class KanbanAssembly extends KanbanAllAssembly {
    public KanbanAssembly() {
        super(new KanbanTemplate(), new KanbanMapping());
    }
}
