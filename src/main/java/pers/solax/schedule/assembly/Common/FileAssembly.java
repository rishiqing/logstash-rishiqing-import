package pers.solax.schedule.assembly.Common;

import pers.solax.schedule.mapping.common.FileMapping;
import pers.solax.schedule.template.common.FileTemplate;

/**
 * Created by solax on 2017-5-8.
 */
public class FileAssembly  extends CommonAssembly {
    public FileAssembly() {
        super(new FileTemplate(), new FileMapping());
    }
}
