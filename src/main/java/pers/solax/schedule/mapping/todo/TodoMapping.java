package pers.solax.schedule.mapping.todo;

import org.elasticsearch.common.settings.Settings;
import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by solax on 2017-4-22.
 */
public class TodoMapping  extends MappingBase {
    protected void mapping() {
        this.subTodoJoinLink();
        this.todoComment();
        this.subTodo();
        this.todo();
    }

    private void subTodoJoinLink () {
        this.setParent("sub_todo_join_link", "sub_todo");
    }

    private void todoComment () {
        //评论  mapping
        MappingEntity todoCommentEntity = new MappingEntity();
        todoCommentEntity.setType("todo_comment");
        todoCommentEntity.setParent("todo_deploy");
        todoCommentEntity.addIKAnalyzer("comment_content");
        this.setMapping(todoCommentEntity);
    }

    private void subTodo () {
        // 子任务 mapping
        MappingEntity subTodoEntity = new MappingEntity();
        subTodoEntity.setType("sub_todo");
        subTodoEntity.setParent("todo_deploy");
        subTodoEntity.addIKAnalyzer("name");
        this.setMapping(subTodoEntity);
    }

    private void todo () {
        // 日程 mapping
        MappingEntity todoEntity = new MappingEntity();
        todoEntity.setType("todo");
        todoEntity.setParent("todo_deploy");
        todoEntity.addIKAnalyzer("receiver_ids");
        todoEntity.addIKAnalyzer("p_title");
        todoEntity.addIKAnalyzer("p_note");
        this.setMapping(todoEntity);
    }
}
