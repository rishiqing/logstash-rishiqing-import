package pers.solax.schedule.mapping.kanban;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanCardMapping extends MappingBase {
    protected void mapping() {
        this.kanbanCard();
    }

    private void kanbanCard () {
        MappingEntity kanbanCardEntity = new MappingEntity();
        kanbanCardEntity.setType("kanban_card");
        kanbanCardEntity.setParent("child_kanban");
        kanbanCardEntity.addIKAnalyzer("name");
        this.setMapping(kanbanCardEntity);
    }
}
