package pers.solax.Base.schedule;

import com.rishiqing.bean.LogStash.LogStash;
import pers.solax.Base.entity.TemplateEntity;
import pers.solax.generator.Generator;
import pers.solax.schedule.BaseSchedule;
import pers.solax.util.Config;
import pers.solax.util.ConfigUtils;

import java.util.*;

/**
 * Created by solax on 2017-3-30.
 */
public abstract class TemplateBase   extends ImportBase {

    final static int PAGE_SIZE =  ConfigUtils.JDBC_PAGE_SIZE;

    private List<TemplateEntity> table = new ArrayList<TemplateEntity>();

    Map schedule = new HashMap();

    protected void addSchedule (String filePath, Object value) {
        schedule.put(filePath, value);
        this.getImportEntity().addTemplateList(filePath);
    }

    protected void generate () {
        Generator generator = new Generator(this.schedule);
        generator.exec();
    }

    private LogStash buildLogStash () {
        LogStash logStash =  new LogStash();
        logStash.set("index", this.indexEntity.getName());
        logStash.set("jdbc_connection_string", ConfigUtils.JDBC_CONNECTION_STRING);
        logStash.set("jdbc_user", ConfigUtils.JDBC_USER);
        logStash.set("jdbc_password", ConfigUtils.JDBC_PASSWORD);
        logStash.set("jdbc_driver_library", env.BASE_PATH + "mysql-connector-java-5.1.39.jar");
        logStash.set("vm", BaseSchedule.VM_CONF);
        return  logStash;
    }

    private void tableTemplate (TemplateEntity templateEntity) {
        LogStash logStash = buildLogStash();
        String type = templateEntity.getType() != null ? templateEntity.getType():templateEntity.getTable();
        logStash.set("document_type", type);
        logStash.set("statement", templateEntity.getExecSql());
        logStash.set("hosts", ConfigUtils.HOSTS);
        if (templateEntity.getDateFieldList() != null) logStash.set("dateFieldList", templateEntity.getDateFieldList());
        if (templateEntity.getHtmlFieldList() != null) logStash.set("htmlFieldList", templateEntity.getHtmlFieldList());
        if (templateEntity.getParentId() != null)  logStash.set("parent", "%{" + templateEntity.getParentId() + "}");
        if (templateEntity.getRouting() != null)  logStash.set("routing", "%{" + templateEntity.getRouting() + "}");
        this.addSchedule(env.BASE_PATH + templateEntity.getTable() + new Date().getTime() + (Math.random() *100) + ".conf", logStash.toMap());
    }

    protected TemplateEntity addTable (String table) {
        return  this.addTable(table, null);
    }

    protected TemplateEntity addTable (String table, String parentId) {
        return this.addTable(table, parentId, "SELECT * from " + table);
    }

    protected TemplateEntity addTable (String table, String parentId, String sql) {
        return this.addTable(table, parentId, sql, null);
    }

    protected TemplateEntity addTable (String table, String parentId, String [] parseHtmlField) {
        return this.addTable(table, parentId, null, parseHtmlField);
    }

    protected TemplateEntity addTable (String table, String parentId, String sql, String [] parseHtmlField) {
        if (sql == null) sql =  "SELECT * from " + table;
        TemplateEntity templateEntity = new TemplateEntity(table, parentId, sql,table, parseHtmlField);
        this.addTable(templateEntity);
        return templateEntity;
    }
    protected void addTable (TemplateEntity templateEntity) {
        this.table.add(templateEntity);
        String sql  = templateEntity.getSql().toLowerCase();
        String maxNumSql = sql.substring(sql.indexOf("from"), sql.length());
        if ( maxNumSql.indexOf(",") > 0) {
            maxNumSql = maxNumSql.substring(0, maxNumSql.indexOf(","));
        }
        if ( maxNumSql.indexOf("where") > 0) {
            maxNumSql = maxNumSql.substring(0, maxNumSql.indexOf("where"));
        }
        templateEntity.setMaxNumSql("select max(id) as max " + maxNumSql);
    }

    private void makeTemplate () {
        for (TemplateEntity templateEntity : this.table) {
            //this.tableTemplate(templateEntity);
            this.templateQueue(templateEntity);
        }
    }

    /**
     * 通过数据库中的条数判断需要生成多少个template
     */
    private void templateQueue (TemplateEntity templateEntity) {
        int num = jdbcQuery.getMaxNum(templateEntity.getMaxNumSql());
        if (num <= PAGE_SIZE) {
           this.tableTemplate(templateEntity);
        } else {
            int t = num / PAGE_SIZE;
            for (int i = 0 ; i <= t; i ++) {
                long index = i * PAGE_SIZE;
                this.addLimit(templateEntity, index);
                this.tableTemplate(templateEntity);
            }
        }
    }

    private void addLimit (TemplateEntity templateEntity,  Long index) {
        String sql = templateEntity.getSql();
        String alias =  sql.substring(sql.toLowerCase().indexOf("select") + 6, sql.indexOf("*"));
        if (sql.toLowerCase().indexOf("where") > 0) {
            templateEntity.setExecSql(templateEntity.getSql() + " and " + alias + " id > " + index + " limit " + PAGE_SIZE);
        } else {
            templateEntity.setExecSql(templateEntity.getSql() + " where " + alias + " id > " + index + " limit " + PAGE_SIZE);
        }

        System.out.println(templateEntity.getExecSql());
    }

    protected abstract void template ();

    public void exec () {
        this.template();
        this.makeTemplate();
        this.generate();
    }
}

