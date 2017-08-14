package pers.solax.schedule.assembly.Common;

import pers.solax.schedule.mapping.common.DepartmentMapping;
import pers.solax.schedule.template.common.DepartmentTemplate;

/**
 * Created by solax on 2017-5-24.
 */
public class DepartmentAssembly extends CommonAssembly  {
    public DepartmentAssembly() {
        super(new DepartmentTemplate(), new DepartmentMapping());
    }
}
