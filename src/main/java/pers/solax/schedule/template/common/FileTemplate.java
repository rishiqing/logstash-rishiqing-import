package pers.solax.schedule.template.common;

import pers.solax.Base.entity.TemplateEntity;
import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-5-8.
 */
public class FileTemplate extends TemplateBase {
    protected void template() {
        this.folderFile();
        this.folder();
        this.noFolderFile();
        this.userFolder();
    }

    private void folderFile () {
        this.addTable("file", "folder_id", "select * from file f where f.folder_id is not null");
    }

    private void folder () {
        this.addTable("folder");
    }

    private void noFolderFile () {
        TemplateEntity commentTemplate = new TemplateEntity();
        commentTemplate.setTable("file");
        commentTemplate.setType("file");
        commentTemplate.setSql("select * from file f where f.folder_id is null");
        commentTemplate.setRouting("id");
        commentTemplate.setParentId("folder_id");
        this.addTable(commentTemplate);
    }

    private void userFolder () {
        this.addTable("user_folder", "folder_id");
    }
}
