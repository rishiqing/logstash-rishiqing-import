package pers.solax.schedule.assembly.corpus;

import pers.solax.schedule.mapping.corpus.SummaryMapping;
import pers.solax.schedule.template.corpus.SummaryTemplate;

/**
 * Created by solax on 2017-4-22.
 */
public class SummaryAssembly extends CorpusAllAssembly {
    public SummaryAssembly() {
        super(new SummaryTemplate(), new SummaryMapping());
    }
}
