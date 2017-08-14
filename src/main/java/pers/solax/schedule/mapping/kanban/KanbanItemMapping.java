package pers.solax.schedule.mapping.kanban;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

/**
 * Created by solax on 2017-4-22.
 */
public class KanbanItemMapping  extends MappingBase{
    protected void mapping() {
        this.kanbanItemComment();
        this.kanbanSubItemJoinLink();
        this.kanbanSubItem();
        this.kanbanItemJoinLink();
        this.kanbanItem();
    }

    /**
     * comment
     * 评论
     */
    private void kanbanItemComment () {
        MappingEntity kanbanItemCommentEntity = new MappingEntity();
        kanbanItemCommentEntity.setType("kanban_item_comment");
        kanbanItemCommentEntity.setParent("kanban_item");
        kanbanItemCommentEntity.addIKAnalyzer("comment_content");
        this.setMapping(kanbanItemCommentEntity);
    }

    private void kanbanSubItemJoinLink () {
        this.setParent("kanban_sub_item_join_link", "kanban_sub_item");
    }

    private void kanbanSubItem () {
        MappingEntity kanbanSubItemEntity = new MappingEntity();
        kanbanSubItemEntity.setType("kanban_sub_item");
        kanbanSubItemEntity.setParent("kanban_item");
        kanbanSubItemEntity.addIKAnalyzer("name");
        this.setMapping(kanbanSubItemEntity);
    }

    private void kanbanItemJoinLink () {
        this.setParent("kanban_item_join_link", "kanban_item");
    }

    private void kanbanItem () {
        MappingEntity kanbanItemEntity = new MappingEntity();
        kanbanItemEntity.setType("kanban_item");
        kanbanItemEntity.setParent("kanban_card");
        kanbanItemEntity.addIKAnalyzer("name");
        kanbanItemEntity.addIKAnalyzer("note");
        this.setMapping(kanbanItemEntity);
    }
}
