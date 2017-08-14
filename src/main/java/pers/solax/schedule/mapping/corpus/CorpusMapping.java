package pers.solax.schedule.mapping.corpus;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

/**
 * Created by solax on 2017-4-22.
 */
public class CorpusMapping extends MappingBase {
    protected void mapping() {
        this.userCorpus();
        this.corpusDepartmentLink();
    }

    private void userCorpus () {
        this.setParent("user_corpus", "corpus");
    }

    private void corpusDepartmentLink () {
        this.setParent("corpus_department_link", "corpus");
    }
}
