package pers.solax.schedule.mapping.corpus;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.entity.TemplateEntity;
import pers.solax.Base.schedule.MappingBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by solax on 2017-4-22.
 */
public class SummaryMapping extends MappingBase{
    protected void mapping() {
        this.comment();
        this.summary();
    }

    private void comment () {
        MappingEntity commentEntity = new MappingEntity();
        commentEntity.setType("summary_comment");
        commentEntity.setParent("summary");
        commentEntity.addIKAnalyzer("comment_content");
        this.setMapping(commentEntity);
    }

    private void summary () {
        MappingEntity mappingEntity = new MappingEntity();
        mappingEntity.setType("summary");
        mappingEntity.setParent("corpus");
        mappingEntity.addIKAnalyzer("user_summary");
        mappingEntity.addIKAnalyzer("title");
        mappingEntity.addIKAnalyzer("content");
        this.setMapping(mappingEntity);
    }
}
