package pers.solax.schedule.assembly.kanban;

import pers.solax.Base.factory.IndexFactory;
import pers.solax.Base.assembly.AssemblyButchBase;
import pers.solax.Base.schedule.MappingBase;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-3-30.
 */
public class KanbanAllAssembly extends AssemblyButchBase {
    private static String alias = IndexFactory.INDEX.KANBAN;

    public KanbanAllAssembly(TemplateBase templateBase, MappingBase mappingBase) {
        super(templateBase, mappingBase, alias);
    }
    public KanbanAllAssembly(){
        this.addBatchRunList(new KanbanItemAssembly());
        this.addBatchRunList(new KanbanCardAssembly());
        this.addBatchRunList(new KanbanChildAssembly());
        this.addBatchRunList(new KanbanAssembly());
    }

    protected  String getAlias () {
        return  this.alias;
    }
}
