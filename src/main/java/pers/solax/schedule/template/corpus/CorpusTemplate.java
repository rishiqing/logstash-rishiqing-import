package pers.solax.schedule.template.corpus;

import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-22.
 */
public class CorpusTemplate extends TemplateBase{
    protected void template() {
        this.corpus();
        this.userCorpus ();
        this.corpusDepartmentLink();
    }
    private void corpus () {
        this.addTable("corpus");
    }

    private void userCorpus () {
        this.addTable("user_corpus", "corpus_id");
    }

    private void corpusDepartmentLink () {
        this.addTable("corpus_department_link", "corpus_id");
    }
}
