package pers.solax.schedule.template.kanban;

import pers.solax.Base.entity.TemplateEntity;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanCardTemplate extends TemplateBase {
    protected void template() {
        this.kanbanCard();
    }

    /**
     * 看板卡片 与 子计划 的 父子关系建立
     */
    private void kanbanCard () {
        TemplateEntity kanbanCardTemplate = new TemplateEntity();
        kanbanCardTemplate.setTable("kanban_card");
        kanbanCardTemplate.setSql(
                "SELECT kc.*, ck.kanban_id as k_id " +
                        "from kanban_card kc, child_kanban ck " +
                        "where kc.child_kanban_id = ck.id");
        kanbanCardTemplate.setParentId("child_kanban_id");
        kanbanCardTemplate.setRouting("k_id");
        this.addTable(kanbanCardTemplate);
    }
}
