package pers.solax.Base.entity;

import com.rishiqing.util.Config;

/**
 * Created by solax on 2017-4-5.
 * version_Index表维护类
 *
 */
public class VersionIndexEntity {
    String indexName = "index_version";

    String type = Config.index;

    Boolean isFirst = false;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }
}
