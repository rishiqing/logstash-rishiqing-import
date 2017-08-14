package pers.solax.Base.schedule;

import pers.solax.Base.Benchmark;
import pers.solax.Base.entity.EnvironmentEntity;
import pers.solax.Base.entity.ImportEntity;
import pers.solax.Base.entity.IndexEntity;
import pers.solax.Base.entity.VersionIndexEntity;
import pers.solax.jdbc.JdbcQuery;

/**
 * Created by solax on 2017-3-30.
 */
public abstract class ImportBase  extends Benchmark {

    protected JdbcQuery jdbcQuery = new JdbcQuery();

    protected IndexEntity indexEntity;

    protected ImportEntity importEntity;

    protected VersionIndexEntity versionIndexEntity;

    protected EnvironmentEntity env;

    public IndexEntity getIndexEntity() {
        return indexEntity;
    }

    public void setIndexEntity(IndexEntity indexEntity) {
        this.indexEntity = indexEntity;
    }

    public ImportEntity getImportEntity() {
        return importEntity;
    }

    public void setImportEntity(ImportEntity importEntity) {
        this.importEntity = importEntity;
    }

    public VersionIndexEntity getVersionIndexEntity() {
        return versionIndexEntity;
    }

    public void setVersionIndexEntity(VersionIndexEntity versionIndexEntity) {
        this.versionIndexEntity = versionIndexEntity;
    }

    public EnvironmentEntity getEnvironmentEntity() {
        return env;
    }

    public void setEnvironmentEntity(EnvironmentEntity environmentEntity) {
        this.env = environmentEntity;
    }

    public EnvironmentEntity getEnv() {
        return env;
    }

    public void setEnv(EnvironmentEntity env) {
        this.env = env;
    }

    public abstract  void exec ();
}
