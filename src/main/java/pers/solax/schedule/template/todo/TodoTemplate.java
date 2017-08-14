package pers.solax.schedule.template.todo;

import pers.solax.Base.entity.TemplateEntity;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-22.
 */
public class TodoTemplate extends TemplateBase {
    protected void template () {
        this.todoDeploy();
        this.todo();
        this.subTodo();
        this.subTodoJoinLink();
        this.todoComment();
    }

    private void todoDeploy () {
        this.addTable("todo_deploy", null,null ,new String [] {"p_note"});
    }

    private void todo () {
        this.addTable("todo", "todo_deploy_id",null,new String [] {"p_note"});
    }

    private void subTodo () {
        this.addTable("sub_todo", "todo_deploy_id");
    }

    private void subTodoJoinLink () {
        TemplateEntity subTodoJoinLinkTemplate = new TemplateEntity();
        subTodoJoinLinkTemplate.setTable("sub_todo_join_link");
        subTodoJoinLinkTemplate.setSql(
                "SELECT stjl.*, st.todo_deploy_id " +
                        "from sub_todo_join_link stjl, sub_todo st " +
                        "where stjl.sub_todo_id = st.id");
        subTodoJoinLinkTemplate.setParentId("sub_todo_id");
        subTodoJoinLinkTemplate.setRouting("todo_deploy_id");
        this.addTable(subTodoJoinLinkTemplate);
    }

    private void todoComment () {
        this.addTable("todo_comment", "todo_deploy_id",new String [] {"comment_content"});
    }
}
