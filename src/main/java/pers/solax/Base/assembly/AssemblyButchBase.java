package pers.solax.Base.assembly;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import pers.solax.Base.schedule.MappingBase;
import pers.solax.Base.schedule.TemplateBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by solax on 2017-4-11.
 */
public abstract class AssemblyButchBase  extends AssemblyBase{

    private boolean isBatch  = true;

    private List<AssemblyBase> batchExec = new ArrayList<AssemblyBase>();

    protected void addBatchRunList (AssemblyBase assemblyBase) {
        this.batchExec.add(assemblyBase);
    }

    public AssemblyButchBase(TemplateBase templateBase, MappingBase mappingBase, String alias) {
        super(templateBase, mappingBase, alias);
    }

    public AssemblyButchBase () {}
    public void registerMapping () {
        if (this.batchExec.size() > 0) {
            this.mapping();
        } else {
            super.registerMapping();
        }
    }
    public void exec () {
        if (this.batchExec.size() > 0) {
            this.run();
        } else {
            super.run();
        }
    }
    protected  boolean getIsBatch () {
        return  isBatch;
    }

    protected void run () {
        for (AssemblyBase assemblyBase : this.batchExec) {
            assemblyBase.setCommand(this.getCommand());
            assemblyBase.exec();
        }
    }

    protected void mapping () {
        for (AssemblyBase assemblyBase : this.batchExec) {
            assemblyBase.setCommand(this.getCommand());
            assemblyBase.registerMapping();
        }
    }
}
