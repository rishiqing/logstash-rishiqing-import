package pers.solax.schedule.template.corpus;

import pers.solax.Base.entity.TemplateEntity;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-22.
 */
public class SummaryTemplate extends TemplateBase {
    protected void template() {
        this.summary();
        this.comment();
    }

    private void summary () {
        // this.addTable("summary", "corpus_id",new String [] {"content"});
        TemplateEntity template = new TemplateEntity();
        template.setTable("summary");
        template.setParentId("corpus_id");
        template.setSql("SELECT * from summary");
        template.setHtmlFieldList(new String [] {"content"});
        template.setDateFieldList(new String [] {"date"});
        this.addTable(template);
    }

    private void comment () {
        TemplateEntity commentTemplate = new TemplateEntity();
        commentTemplate.setTable("comment");
        commentTemplate.setType("summary_comment");
        commentTemplate.setSql("SELECT c.*, s.corpus_id " +
                "from comment c, summary s " +
                "where c.summary_id = s.id " +
                "and c.summary_id is not null");
        commentTemplate.setParentId("summary_id");
        commentTemplate.setRouting("corpus_id");
        commentTemplate.setHtmlFieldList(new String [] {"comment_content"});
        this.addTable(commentTemplate);
    }
}
