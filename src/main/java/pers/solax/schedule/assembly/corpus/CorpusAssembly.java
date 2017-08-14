package pers.solax.schedule.assembly.corpus;

import pers.solax.schedule.mapping.corpus.CorpusMapping;
import pers.solax.schedule.template.corpus.CorpusTemplate;

/**
 * Created by solax on 2017-4-22.
 */
public class CorpusAssembly extends CorpusAllAssembly {
    public CorpusAssembly() {
        super(new CorpusTemplate(), new CorpusMapping());
    }
}
