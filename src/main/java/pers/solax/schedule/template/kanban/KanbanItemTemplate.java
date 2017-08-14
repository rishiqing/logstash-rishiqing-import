package pers.solax.schedule.template.kanban;

import pers.solax.Base.entity.TemplateEntity;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanItemTemplate extends TemplateBase {
    protected void template() {
        this.kanbanItem();
        this.kanbanItemJoinLink();
        this.kanbanSubItem();
        this.kanbanSubItemJoinLink();
        this.comment();
    }

    /**
     * kanban_item
     * 看板任务 与 看板卡片的父子关系
     */
    private  void kanbanItem () {
        TemplateEntity kanbanItemTemplate = new TemplateEntity();
        kanbanItemTemplate.setTable("kanban_item");
        kanbanItemTemplate.setSql(
                "SELECT ki.*, ck.kanban_id " +
                        "from kanban_item ki,kanban_card kc,child_kanban ck " +
                        "where kc.id = ki.kanban_card_id and ck.id = kc.child_kanban_id");
        kanbanItemTemplate.setParentId("kanban_card_id");
        kanbanItemTemplate.setRouting("kanban_id");
        this.addTable(kanbanItemTemplate);
    }

    /**
     * kanban_item_join_link
     * 看板任务成员 与 看板任务的父子关系
     */
    private  void kanbanItemJoinLink () {
        TemplateEntity kanbanItemJoinLinkTemplate = new TemplateEntity();
        kanbanItemJoinLinkTemplate.setTable("kanban_item_join_link");
        kanbanItemJoinLinkTemplate.setSql(
                "SELECT kijl.*, ck.kanban_id " +
                        "from kanban_item_join_link kijl,kanban_item ki,kanban_card kc,child_kanban ck " +
                        "where kijl.kanban_item_id = ki.id and kc.id = ki.kanban_card_id and ck.id = kc.child_kanban_id");
        kanbanItemJoinLinkTemplate.setParentId("kanban_item_id");
        kanbanItemJoinLinkTemplate.setRouting("kanban_id");
        this.addTable(kanbanItemJoinLinkTemplate);
    }

    /**
     * kanban_sub_item
     * 看板任务子任务 与 看板任务的父子关系
     */
    private  void kanbanSubItem () {
        TemplateEntity kanbanSubItem = new TemplateEntity();
        kanbanSubItem.setTable("kanban_sub_item");
        kanbanSubItem.setSql(
                "SELECT ksi.*, ck.kanban_id " +
                        "from kanban_sub_item ksi, kanban_item ki, kanban_card kc, child_kanban ck " +
                        "where ksi.kanban_item_id = ki.id and kc.id = ki.kanban_card_id and ck.id = kc.child_kanban_id");
        kanbanSubItem.setParentId("kanban_item_id");
        kanbanSubItem.setRouting("kanban_id");
        this.addTable(kanbanSubItem);
    }

    /**
     * kanban_sub_item_join_link
     * 看板任务子任务成员 与 看板任务子任务
     */
    private void kanbanSubItemJoinLink () {
        TemplateEntity kanbanSubItemJoinLink = new TemplateEntity();
        kanbanSubItemJoinLink.setTable("kanban_sub_item_join_link");
        kanbanSubItemJoinLink.setSql(
                "SELECT ksijl.*, " +
                        "ck.kanban_id " +
                        "from " +
                        "kanban_sub_item_join_link ksijl, " +
                        "kanban_sub_item ksi, " +
                        "kanban_item ki, " +
                        "kanban_card kc," +
                        "child_kanban ck " +
                        "where" +
                        " ksijl.kanban_sub_item_id = ksi.id " +
                        "and ksi.kanban_item_id = ki.id " +
                        "and kc.id = ki.kanban_card_id " +
                        "and ck.id = kc.child_kanban_id");
        kanbanSubItemJoinLink.setParentId("kanban_sub_item_id");
        kanbanSubItemJoinLink.setRouting("kanban_id");
        this.addTable(kanbanSubItemJoinLink);
    }

    /**
     * commnet
     * 看板任务评论 与 看板任务
     */
    private void comment () {
        TemplateEntity commentTemplate = new TemplateEntity();
        commentTemplate.setTable("comment");
        commentTemplate.setType("kanban_item_comment");
        commentTemplate.setSql(
                "SELECT c.*, ck.kanban_id " +
                        "from comment c, kanban_item ki, kanban_card kc,child_kanban ck " +
                        "where c.kanban_item_id = ki.id and kc.id = ki.kanban_card_id and ck.id = kc.child_kanban_id");
        commentTemplate.setParentId("kanban_item_id");
        commentTemplate.setRouting("kanban_id");
        commentTemplate.setHtmlFieldList(new String [] {"comment_content"});
        this.addTable(commentTemplate);
    }
}
