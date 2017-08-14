package pers.solax.Base.entity;

/**
 * Created by solax on 2017-3-30.
 * version_index 表中每条记录的维护表
 */
public class IndexEntity {
    String id;

    String name;

    String alias;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
