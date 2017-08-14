package pers.solax.schedule.assembly.Common;

import pers.solax.schedule.mapping.common.RecycleBinMapping;
import pers.solax.schedule.template.common.RecycleBinTemplate;

/**
 * Created by solax on 2017-4-24.
 */
public class RecycleBinAssembly  extends CommonAssembly  {
    public RecycleBinAssembly() {
        super(new RecycleBinTemplate(), new RecycleBinMapping());
    }
}
