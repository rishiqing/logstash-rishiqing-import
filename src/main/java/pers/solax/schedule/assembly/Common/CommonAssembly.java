package pers.solax.schedule.assembly.Common;

import pers.solax.Base.factory.IndexFactory;
import pers.solax.Base.assembly.AssemblyButchBase;
import pers.solax.Base.schedule.MappingBase;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-24.
 */
public class CommonAssembly extends AssemblyButchBase {
    private static String alias =  IndexFactory.INDEX.COMMON;;

    public CommonAssembly(TemplateBase templateBase, MappingBase mappingBase) {
        super(templateBase, mappingBase, alias);
    }

    public CommonAssembly () {
        this.addBatchRunList(new DepartmentAssembly());
        this.addBatchRunList(new ArchiveAssembly());
        this.addBatchRunList(new RecycleBinAssembly());
        this.addBatchRunList(new FileAssembly());
        this.addBatchRunList(new UserAssembly());
    }

    protected String getAlias() {
        return this.alias;
    }
}
