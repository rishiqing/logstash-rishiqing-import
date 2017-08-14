package pers.solax.Base.factory;

import com.rishiqing.util.Config;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import pers.solax.Base.Bench;
import pers.solax.Base.Benchmark;
import pers.solax.Base.entity.IndexConstant;
import pers.solax.Base.entity.IndexEntity;
import pers.solax.Base.entity.VersionIndexEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by solax on 2017-5-5.
 * 表管理类
 * 所有表数据都应该从这里获得
 * 单例类
 */
public class IndexFactory extends Benchmark {

    private static Map <String, Object> ignoreList = new HashMap<String, Object>();
    // 版本数据库信息
    private VersionIndexEntity versionIndexEntity = new VersionIndexEntity();

    // 数据库信息
    private Map<String, IndexEntity> indexMap = new HashMap<String, IndexEntity>();

    // 数据库名称
    public static final IndexConstant INDEX = new IndexConstant();

    // 数据库名称的list
    private List<String> indexNameList = new ArrayList<String>();

    // 记录修改过的index,需要在最后时刻重置alias
    private List<IndexUpdateEntity> indexUpdateList = new ArrayList<IndexUpdateEntity>();

    private static  IndexFactory factory;

    static {
        ignoreList.put("lala", "lala");
    }

    private IndexFactory () {
        this.generate();
    }

    public  static IndexFactory getInstance () {
        if (factory == null) {
            factory = new IndexFactory();
        }
        return factory;
    }

    private void generate () {
        // 将数据库名称转换为list
        this.initIndexNameList();
        // 初始化versionIndex数据
        this.initVersionIndex();
        // 建立表数据结构
        this.initIndexStructure();
        // 检查并补充完整数据结构
        this.parseStructure();
    }

    /**
     * 初始化versionIndex数据
     * 1.判断versionIndex是否存在
     * 2.不存在则建立
     */
    private void initVersionIndex () {
        if (!this.indexExist(versionIndexEntity.getIndexName())) {
            this.createIndex(versionIndexEntity.getIndexName());
        }
    }

    /**
     * 初始化表结构数据
     *
     *
     */
    private void initIndexStructure () {
        // 查询versionIndex中的所有数据
        SearchHits searchHits = this.searchVersionIndex();
        // 结构化数据
        this.structureIndex(searchHits);
        // 对结构化数据进行处理，确认数据完整性
    }

    /**
     * 查询versionIndex数据
     * @return
     */
    private SearchHits searchVersionIndex () {
        SearchResponse response  = this.esClient.prepareSearch(this.versionIndexEntity.getIndexName())
                .setTypes(Config.index)
                .get();
        SearchHits searchHits = response.getHits();
        return searchHits;
    }

    /**
     * 构建indexMap
     * @param searchHits
     */
     private void  structureIndex (SearchHits searchHits) {
         SearchHit[] hits = searchHits.getHits();
         if (hits.length > 0) {
             for (SearchHit hit : hits) {
                 Map map = hit.getSource();
                 String alias = map.get("alias").toString();

                 IndexEntity indexEntity = new IndexEntity();
                 indexEntity.setId(hit.getId());
                 indexEntity.setAlias(alias);
                 indexEntity.setName(map.get("name").toString());
                 if (ignoreList.get(alias) == null) {
                     this.indexMap.put(alias, indexEntity);
                 } else {
                     ignoreList.put(alias, indexEntity);
                 }
             }
         }
     }

    /**
     * 将数据库名称转化为list
     */
    private void initIndexNameList () {
        try {
            Field[] field = IndexConstant.class.getDeclaredFields();
            for (Field oneField : field) {
                oneField.setAccessible(true);
                this.indexNameList.add(oneField.get(this).toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查并补充indexMap结构完整性
     * 发现缺少index，则建立并创建
     */
    private void parseStructure () {
        for (String index : this.indexNameList) {
            IndexEntity indexEntity = this.indexMap.get(index);
            if (indexEntity == null) {
                // 创建index 并加入到数据结构中
                this.addIndex(index);
            }
        }
    }

    /**
     * 向数据结构中添加一个index,
     * 1.建立一个indexEntity
     * 2.插入该 index 到数据库中
     * 3.将indexEntity加入到数据结构中，
     * 4.记录到更新记录中
     * @param index
     */
    public void addIndex (String index) {
        //  建立基础数据模型
        IndexEntity indexEntity = new IndexEntity();
        indexEntity.setName(this.generateIndexName(index));
        indexEntity.setAlias(index);
        indexEntity.setId(index);
        // 判断原数据库表名是否存在
        String oldIndexName = null;
        try{
            IndexEntity orgIndexEntity =  (IndexEntity)this.ignoreList.get(index);
            if (orgIndexEntity != null) {
                oldIndexName = orgIndexEntity.getName();
            }
        } catch (Exception e) {}
        // 建立这个数据库
        this.createIndex(indexEntity.getName());
        // 加入到数据结构中，或者是覆盖
        this.indexMap.put(index, indexEntity);
        // 记录到表更新记录中
        this.indexUpdateList.add(this.getIndexUpdateEntity(indexEntity, oldIndexName));
    }

    /**
     * 执行更新或插入index语句到数据库中
     */
    public void updateVersionIndex (IndexEntity indexEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alias", indexEntity.getAlias());
        map.put("name", indexEntity.getName());
        IndexResponse indexResponse = this.esClient.getTransportClient().prepareIndex(this.versionIndexEntity.getIndexName(), this.versionIndexEntity.getType())
                .setSource(map)
                .setId(indexEntity.getId())
                .get();
        //logger.debug(indexEntity.getName() + " - index result - " + indexResponse.toString());
        System.out.println(indexEntity.getName() + " - index result - " + indexResponse.toString());
    }
    /**
     * 根据规则生成一个index名称
     * @param index
     * @return
     */
    private String generateIndexName (String index) {
        return  index+ "_" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }

    /**
     * 生成一个表名更新记录对象
     * @param indexEntity
     * @return
     */
    private IndexUpdateEntity getIndexUpdateEntity (IndexEntity indexEntity) {
        return this.getIndexUpdateEntity(indexEntity, null);
    }

    /**
     * 生成一个表名更新记录对象
     * @param indexEntity
     * @param oldName
     * @return
     */
    private IndexUpdateEntity getIndexUpdateEntity (IndexEntity indexEntity, String oldName) {
        IndexUpdateEntity indexUpdateEntity = new IndexUpdateEntity();
        indexUpdateEntity.alias = indexEntity.getAlias();
        indexUpdateEntity.newName = indexEntity.getName();
        indexUpdateEntity.oldName = oldName;
        return indexUpdateEntity;
    }


    /**
     * 批量执行reloadAliasOne
     */
    public void reloadAlias () {
        for (IndexUpdateEntity indexUpdateEntity : this.indexUpdateList) {
            this.reloadAliasOne(indexUpdateEntity);
        }
    }

    /**
     * 1.更新version_index表中的 ｛alias,name｝记录
     * 2.根据更新记录，更新数据库中的表级alias
     */
    private void reloadAliasOne (IndexUpdateEntity indexUpdateEntity) {
        // 获得数据源
        IndexEntity indexEntity = this.indexMap.get(indexUpdateEntity.alias);
        // 更新version_index
        this.updateVersionIndex(indexEntity);
        // 删除原表级alias
        if (indexUpdateEntity.oldName != null) {
            this.removeElasticAlias(indexUpdateEntity);
        }
        // 建立新的表级alias
        this.addElasticAlias(indexUpdateEntity);
    }

    /**
     * 建立表级alias
     * @param indexUpdateEntity
     */
    private void addElasticAlias (IndexUpdateEntity indexUpdateEntity) {
        this.adminClient.prepareAliases()
                .addAlias(
                        indexUpdateEntity.newName,
                        indexUpdateEntity.alias)
                .execute().actionGet();
    }

    /**
     * 删除表级alias
     * @param indexUpdateEntity
     */
    private void removeElasticAlias (IndexUpdateEntity indexUpdateEntity) {
        this.adminClient.prepareAliases()
                .removeAlias(
                        indexUpdateEntity.oldName,
                        indexUpdateEntity.alias)
                .execute().actionGet();
    }
    public  static  void addIgnore (String index) {
        String alias = Config.index + "_" + index;
        ignoreList.put(alias, alias);
    }

    public IndexEntity  getIndexEntity (String key) {
        return this.indexMap.get(key);
    }

    public VersionIndexEntity getVersionIndexEntity () {
        return this.versionIndexEntity;
    }

    public Map<String, IndexEntity> getIndexMap () {
        return  this.indexMap;
    }
}

class IndexUpdateEntity {
    public String alias;

    public String oldName;

    public String newName;
}