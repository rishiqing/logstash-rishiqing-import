package pers.solax.schedule.mapping.common;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

/**
 * Created by solax on 2017-4-24.
 */
public class ArchiveMapping extends MappingBase{
    protected void mapping() {
        this.archiveFile();
    }

    private void archiveFile () {
        MappingEntity mappingEntity = new MappingEntity();
        mappingEntity.setType("archive_file");
        mappingEntity.setParent("archive_bag");
        mappingEntity.addIKAnalyzer("user_authority");
        mappingEntity.addIKAnalyzer("title");
        mappingEntity.addIKAnalyzer("note");
        this.setMapping(mappingEntity);
    }
}
