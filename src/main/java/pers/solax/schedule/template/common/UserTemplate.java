package pers.solax.schedule.template.common;

import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-8-10.
 */
public class UserTemplate extends TemplateBase {
    protected void template() {
        this.user();
        this.teamUserMessages();
    }

    private void user () {
        this.addTable("user");
    }

    private  void teamUserMessages () {
        this.addTable("team_user_messages", "user_id");
    }
}
