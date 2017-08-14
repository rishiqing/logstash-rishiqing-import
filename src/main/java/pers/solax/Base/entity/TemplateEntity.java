package pers.solax.Base.entity;

/**
 * Created by solax on 2017-5-8.
 */
public class TemplateEntity {
    String table;

    String parentId;

    String sql;

    String execSql;

    String maxNumSql;

    String type;

    String [] htmlFieldList;

    String [] dateFieldList;

    String routing;

    public TemplateEntity () {

    }

    public TemplateEntity(String table, String parentId, String sql, String type, String[] htmlFieldList) {
        this.table = table;
        this.parentId = parentId;
        this.sql = sql;
        this.execSql = sql;
        this.type = type;
        this.htmlFieldList = htmlFieldList;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
        this.execSql = sql;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getHtmlFieldList() {
        return htmlFieldList;
    }

    public void setHtmlFieldList(String[] htmlFieldList) {
        this.htmlFieldList = htmlFieldList;
    }

    public String[] getDateFieldList() {
        return dateFieldList;
    }

    public void setDateFieldList(String[] dateFieldList) {
        this.dateFieldList = dateFieldList;
    }

    public String getRouting() {
        return routing;
    }

    /**
     * 很关键
     * @param routing
     * @author solax
     */
    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getExecSql() {
        return execSql;
    }

    public void setExecSql(String execSql) {
        this.execSql = execSql;
    }

    public String getMaxNumSql() {
        return maxNumSql;
    }

    public void setMaxNumSql(String maxNumSql) {
        this.maxNumSql = maxNumSql;
    }
}
