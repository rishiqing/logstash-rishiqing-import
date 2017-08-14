package pers.solax.schedule.mapping.kanban;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanChildMapping extends MappingBase {
    protected void mapping() {
        this.childKanban();
    }

    private void childKanban () {
        this.setParent("child_kanban", "kanban");
    }
}
