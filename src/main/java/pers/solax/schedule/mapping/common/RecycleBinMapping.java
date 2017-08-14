package pers.solax.schedule.mapping.common;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

/**
 * Created by solax on 2017-4-24.
 */
public class RecycleBinMapping extends MappingBase {
    protected void mapping() {
        MappingEntity mappingEntity = new MappingEntity();
        mappingEntity.setType("recycle_bin");
        mappingEntity.addIKAnalyzer("user_authority");
        mappingEntity.addIKAnalyzer("title");
        mappingEntity.addIKAnalyzer("note");
        this.setMapping(mappingEntity);
    }
}
