package pers.solax.schedule.assembly.corpus;

import pers.solax.Base.entity.IndexConstant;
import pers.solax.Base.factory.IndexFactory;
import pers.solax.Base.assembly.AssemblyButchBase;
import pers.solax.Base.schedule.MappingBase;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-22.
 */
public class CorpusAllAssembly  extends AssemblyButchBase {
    private static   String alias = IndexFactory.INDEX.CORPUS;

    public CorpusAllAssembly(TemplateBase templateBase, MappingBase mappingBase) {
        super(templateBase, mappingBase, alias);
    }

    public CorpusAllAssembly(){
        this.addBatchRunList(new SummaryAssembly());
        this.addBatchRunList(new CorpusAssembly());
    }

    protected String getAlias() {
        return this.alias;
    }
}
