package pers.solax.schedule.mapping.kanban;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by solax on 2017-3-30.
 */
public class KanbanMapping extends MappingBase {
    protected void mapping() {
        this.kanbanUserLink();
        this.kanbanDepartmentLink();
        this.kanban();
    }

    private void kanbanUserLink () {
        this.setParent("kanban_user_link", "kanban");
    }

    private void kanbanDepartmentLink () {
        this.setParent("kanban_department_link", "kanban");
    }

    private void kanban () {
        MappingEntity kanbanEntity = new MappingEntity();
        kanbanEntity.setType("kanban");
        kanbanEntity.addIKAnalyzer("access_ids");
        kanbanEntity.addIKAnalyzer("name");
        this.setMapping(kanbanEntity);
    }
}
