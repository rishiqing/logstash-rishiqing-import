package pers.solax.schedule.mapping.common;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

/**
 * Created by solax on 2017-5-24.
 */
public class DepartmentMapping  extends MappingBase {
    protected void mapping() {
        this.department();
    }

    private void department () {
        MappingEntity departmentEntity = new MappingEntity();
        departmentEntity.setType("department");
        departmentEntity.addIKAnalyzer("code");
        departmentEntity.addIKAnalyzer("name");
        departmentEntity.addIKAnalyzer("short_name");
        this.setMapping(departmentEntity);
    }
}
