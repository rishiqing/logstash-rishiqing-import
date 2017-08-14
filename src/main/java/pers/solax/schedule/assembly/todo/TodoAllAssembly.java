package pers.solax.schedule.assembly.todo;

import pers.solax.Base.factory.IndexFactory;
import pers.solax.Base.assembly.AssemblyButchBase;
import pers.solax.Base.schedule.MappingBase;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-22.
 */
public class TodoAllAssembly extends AssemblyButchBase {
    private static   String alias = IndexFactory.INDEX.TODO;

    public TodoAllAssembly(TemplateBase templateBase, MappingBase mappingBase) {
        super(templateBase, mappingBase, alias);
    }

    public TodoAllAssembly(){
        this.addBatchRunList(new TodoAssembly());
    }

    protected String getAlias() {
        return this.alias;
    }
}
