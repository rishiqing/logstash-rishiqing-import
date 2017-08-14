package pers.solax.schedule.template.common;

import pers.solax.Base.schedule.TemplateBase;

/**
 * Created by solax on 2017-4-24.
 */
public class ArchiveTemplate extends TemplateBase{
    protected void template() {
        this.archiveBag();
        this.archiveFile();
    }

    private void archiveBag () {
        this.addTable("archive_bag");
    }

    private void archiveFile () {
        // 日程
        this.addTable("archive_file", "archive_bag_id", "select af.*, t.p_note as note from archive_file af, todo t where t.id = af.level_one_id and af.type = 3");
        // 计划任务
        this.addTable("archive_file", "archive_bag_id", "select af.*, ki.note as note from archive_file af, kanban_item ki where ki.id = af.level_four_id and af.type = 6");
        // 其他全部
        this.addTable("archive_file", "archive_bag_id", "select af.* from archive_file af where af.type <> 3 and af.type <> 6 ");
    }
}
