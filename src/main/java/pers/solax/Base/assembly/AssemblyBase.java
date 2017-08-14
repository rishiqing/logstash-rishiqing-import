package pers.solax.Base.assembly;

import pers.solax.Base.Benchmark;
import pers.solax.Base.entity.EnvironmentEntity;
import pers.solax.Base.entity.ImportEntity;
import pers.solax.Base.entity.IndexEntity;
import pers.solax.Base.entity.VersionIndexEntity;
import pers.solax.Base.factory.IndexFactory;
import pers.solax.Base.schedule.MappingBase;
import pers.solax.Base.schedule.TemplateBase;
import pers.solax.entry.Command;
import pers.solax.util.FileUtil;
import pers.solax.util.Run;
import pers.solax.util.LrUtils;

import java.util.*;

/**
 * Created by solax on 2017-3-30.
 */
public abstract class AssemblyBase extends Benchmark {

    private Command command;

    private Boolean isBatch = false;
    // 导入使用到的变量
    protected ImportEntity importEntity = new ImportEntity();
    // 版本库每条记录
    protected IndexEntity indexEntity = null;
    // 版本库
    protected VersionIndexEntity versionIndexEntity = null;
    // 环境变量
    protected EnvironmentEntity environmentEntity = new EnvironmentEntity();

    protected TemplateBase templateBase;

    protected MappingBase mappingBase;

    protected String alias;

/*    public  AssemblyBase (TemplateBase templateBase,
                          MappingBase mappingBase) {
        this.initializeBean(templateBase, mappingBase);
        this.setValue();
    }*/

    public  AssemblyBase (TemplateBase templateBase,
                          MappingBase mappingBase,
                          String alias) {
        this.alias = alias;
        this.initializeBean(templateBase, mappingBase);
        this.setValue();
    }

    private void initializeBean (TemplateBase templateBase, MappingBase mappingBase) {
        this.templateBase = templateBase;
        this.mappingBase = mappingBase;
    }

    private  void setValue () {
        // 初始化两个关键属性
        IndexFactory indexFactory = IndexFactory.getInstance();
        this.indexEntity = indexFactory.getIndexEntity(this.alias);
        this.versionIndexEntity = indexFactory.getVersionIndexEntity();

        this.templateBase.setIndexEntity(indexEntity);
        this.templateBase.setEnvironmentEntity(environmentEntity);
        this.templateBase.setImportEntity(importEntity);
        this.templateBase.setVersionIndexEntity(versionIndexEntity);

        if (this.mappingBase != null) this.mappingBase.setIndexEntity(indexEntity);
        if (this.mappingBase != null) this.mappingBase.setImportEntity(importEntity);
        if (this.mappingBase != null) this.mappingBase.setEnvironmentEntity(environmentEntity);
        if (this.mappingBase != null) this.mappingBase.setVersionIndexEntity(versionIndexEntity);
    }

    protected  boolean getIsBatch () {
        return  isBatch;
    }

    protected AssemblyBase(){}


    public void registerMapping () {
        if (this.mappingBase != null) this.mappingBase.exec();
    }

    public void exec () {
        this.run();
    }

    protected void run () {
        this.templateBase.exec();
        this.copyMysqlJDBC();
        this.runLogStash();
    }

    private void copyMysqlJDBC () {
        FileUtil.copy( this.getClass().getResourceAsStream("/mysql-connector-java-5.1.39.jar"), this.environmentEntity.BASE_PATH +"mysql-connector-java-5.1.39.jar");
     //   InputStream inputStream = this.getClass().getResourceAsStream("/mysql-connector-java-5.1.39.jar");
      //  System.out.println(path);
      //  FileUtil.copy(path, this.environmentEntity.BASE_PATH +"mysql-connector-java-5.1.39.jar");
    }

    /**
     * 运行logsatsh
     */
    private void runLogStash () {
        List<String> fileList = importEntity.getTemplateList();
        for (String file :  fileList) {
            if (LrUtils.isWindows()) {
                Run.exec(environmentEntity.LOGSTASH_HOME + "logstash.bat -f " + file, command.isQuiet());
            } else {
                Run.exec(environmentEntity.LOGSTASH_HOME + "logstash -f " + file, command.isQuiet());
            }
            logger.info("import finished - " + file);
        }
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}
