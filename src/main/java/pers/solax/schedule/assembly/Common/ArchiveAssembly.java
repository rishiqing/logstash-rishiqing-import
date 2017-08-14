package pers.solax.schedule.assembly.Common;

import com.rishiqing.util.Config;
import pers.solax.Base.assembly.AssemblyBase;
import pers.solax.schedule.mapping.common.ArchiveMapping;
import pers.solax.schedule.template.common.ArchiveTemplate;

/**
 * Created by solax on 2017-4-24.
 */
public class ArchiveAssembly extends CommonAssembly {
    public ArchiveAssembly() {
        super(new ArchiveTemplate(), new ArchiveMapping());
    }
}
