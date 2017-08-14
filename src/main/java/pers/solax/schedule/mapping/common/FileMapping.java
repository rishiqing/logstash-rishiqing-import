package pers.solax.schedule.mapping.common;

import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.schedule.MappingBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by solax on 2017-5-8.
 */
public class FileMapping extends MappingBase {
    protected void mapping() {
        this.userFolder();
        this.file();
        this.folder();
    }

    private void file () {
        MappingEntity fileEntity = new MappingEntity();
        fileEntity.setType("file");
        fileEntity.setParent("folder");
        fileEntity.addIKAnalyzer("file_name");
        this.setMapping(fileEntity);
    }

    private void folder () {
        MappingEntity mappingEntity = new MappingEntity();
        mappingEntity.setType("folder");
        mappingEntity.addIKAnalyzer("user_folder");
        mappingEntity.addIKAnalyzer("name");
        this.setMapping(mappingEntity);
    }
    private void userFolder () {
        this.setParent("user_folder", "folder");
    }
}
