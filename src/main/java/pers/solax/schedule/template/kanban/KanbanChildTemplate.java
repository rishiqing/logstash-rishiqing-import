package pers.solax.schedule.template.kanban;

import pers.solax.Base.entity.TemplateEntity;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanChildTemplate extends TemplateBase {
    protected void template() {
        // 子计划 与 看板 的 父子关系建立
        this.addTable("child_kanban", "kanban_id");
    }
}
