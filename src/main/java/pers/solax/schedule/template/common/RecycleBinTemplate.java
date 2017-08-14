package pers.solax.schedule.template.common;

import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-24.
 */
public class RecycleBinTemplate extends TemplateBase {
    protected void template() {
        //  日程任务特殊处理
        this.addTable("recycle_bin", null, "select rb.*, t.p_note as note from recycle_bin rb, todo t where t.id = rb.level_one_id and rb.type = 3 ");
        // 计划任务特殊处理
        this.addTable("recycle_bin", null, "select rb.*, ki.note  as note from recycle_bin rb, kanban_item ki where ki.id = rb.level_four_id and rb.type = 6 ");
        // 其他全部
        this.addTable("recycle_bin", null, "select rb.* from recycle_bin rb where rb.type <> 3 and rb.type <> 6 ");
    }
}
