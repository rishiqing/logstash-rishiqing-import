package pers.solax.schedule.template.kanban;

import com.rishiqing.bean.LogStash.LogStash;
import pers.solax.Base.schedule.TemplateBase;
import pers.solax.schedule.BaseSchedule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by solax on 2017-3-30.
 */
public class KanbanTemplate extends TemplateBase {
    protected void template () {
        // 看板
        this.addTable("kanban");
        // 看板用户关联表
        this.addTable("kanban_user_link", "kanban_id");
        // 看病难部门关联表
        this.addTable("kanban_department_link", "kanban_id");
    }
}
