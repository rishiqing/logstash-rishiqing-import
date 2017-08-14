package pers.solax.Base.factory;
import com.carrotsearch.hppc.ObjectLookupContainer;
import com.carrotsearch.hppc.cursors.ObjectCursor;
import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.xcontent.XContentBuilder;
import pers.solax.Base.Benchmark;
import pers.solax.Base.entity.IndexEntity;
import pers.solax.Base.entity.MappingEntity;
import java.util.*;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by solax on 2017-5-5.
 */
public class MappingFactory extends Benchmark {
    private static MappingFactory factory;

    private List<MappingEntity> mappings = new ArrayList<MappingEntity>();

    private IndexFactory indexFactory = IndexFactory.getInstance();

    private MappingFactory () {

    }

    public  static MappingFactory getInstance () {
        if (factory == null) {
            factory = new MappingFactory();
        }
        return factory;
    }

    public void addMapping (MappingEntity mappingEntity) {
        this.mappings.add(mappingEntity);
    }

    public void createMapping (MappingEntity mappingEntity) {
        try {
            XContentBuilder mapping =  jsonBuilder();
            mapping.startObject();
            mapping.startObject(mappingEntity.getType());
            if (mappingEntity.getParent() != null) {
                mapping.startObject("_parent");
                mapping.field("type", mappingEntity.getParent());
                mapping.endObject();
            }
            Map<String, Map<String, String>> properties = mappingEntity.getProperties();
            if (properties.size() > 0) {
                mapping.startObject("properties");
                for (String field : properties.keySet()) {
                    Map <String, String > fieldValue = properties.get(field);
                    mapping.startObject(field);
                    for (String name : fieldValue.keySet()) {
                        String value = fieldValue.get(name);
                        mapping.field(name, value);
                    }
                    mapping.endObject();
                }
                mapping.endObject();
            }
            mapping.endObject();
            mapping.endObject();
            PutMappingRequest mappingRequest = Requests.putMappingRequest(mappingEntity.getIndex())
                    .type(mappingEntity.getType())
                    .source(mapping);
            PutMappingResponse response = this.getAdminClient().putMapping(mappingRequest).actionGet();
            System.out.println("update - mapping " +  response.isAcknowledged() +" index - " + mappingEntity.getIndex() + " - type - " + mappingEntity.getType()  + " - parent - " + mappingEntity.getParent());
        } catch (Exception e) {
            e.printStackTrace();
        }
//{"" + field + "": {"type": "text","analyzer": "ik_max_word" }}

/*        System.out.println("mapping start : " + index + " - type - " + type  + " - parent - " + parentType);
        String map ="{\"" + type + "\":{\"_parent\":{\"type\":\"" + parentType +"\"}}}";
      //  logger.info("insert mapping : " + type + " - parent: " + parentType );
        PutMappingRequest mappingRequest = Requests.putMappingRequest(index)
                .type(type)
                .source(map);
        this.getAdminClient().putMapping(mappingRequest).actionGet();
        // logger.debug("update - mapping index - " + index + " - type - " + type  + " - parent - " + parentType);
        System.out.println("update - mapping index - " + index + " - type - " + type  + " - parent - " + parentType);*/
    }
    /**
     * 存在一些失效的mapping,只有子类，没有父类
     */
    public void validateMapping (MappingEntity mappingEntity) {
        MappingEntity mapping = new MappingEntity();
        mapping.setIndex(mappingEntity.getIndex());
        if (mappingEntity.getParent() != null) {
            mapping.setType(mappingEntity.getParent());
            if (!this.hasMapping(mapping)) {
                this.createMapping(mapping);
            }
        }
    }

    public void syncParentMapping () {
        for (MappingEntity mappingEntity : this.mappings) {
            this.execParentMapping(mappingEntity);
        }
        for (MappingEntity mappingEntity : this.mappings) {
            this.execValidateMapping(mappingEntity);
        }
    }

    private void execParentMapping (MappingEntity mappingEntity) {
        Map<String, IndexEntity> indexMap = this.indexFactory.getIndexMap();
        for (String key : indexMap.keySet()) {
            IndexEntity indexEntity = indexMap.get(key);
            mappingEntity.setIndex(indexEntity.getName());
            this.createMapping(mappingEntity);
        }
    }

    private void execValidateMapping (MappingEntity mappingEntity) {
        Map<String, IndexEntity> indexMap = this.indexFactory.getIndexMap();
        for (String key : indexMap.keySet()) {
            IndexEntity indexEntity = indexMap.get(key);
            mappingEntity.setIndex(indexEntity.getName());
            this.validateMapping(mappingEntity);
        }
    }

    /**
     * 判断mapping是否存在
     * @param mappingEntity
     * @return
     */
    private boolean hasMapping (MappingEntity mappingEntity) {
        boolean result = false;
        GetMappingsRequest getMappingsRequest =  new GetMappingsRequest();
        getMappingsRequest.indices(mappingEntity.getIndex());
        getMappingsRequest.types(mappingEntity.getType());
        try {
            GetMappingsResponse response = this.getAdminClient().getMappings(getMappingsRequest).get();
            ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetaData>> mappings = response.getMappings();
            Iterator<ObjectObjectCursor<String, ImmutableOpenMap<String, MappingMetaData>>>
                    mappingIterator = mappings.iterator();
            while (mappingIterator.hasNext()) {
                ObjectObjectCursor<String, ImmutableOpenMap<String, MappingMetaData>>
                        objectObjectCursor = mappingIterator.next();
                ImmutableOpenMap<String, MappingMetaData> immutableOpenMap = objectObjectCursor.value;
                ObjectLookupContainer<String> keys = immutableOpenMap.keys();
                Iterator<ObjectCursor<String>> keysIterator = keys.iterator();
                while(keysIterator.hasNext()) {
                    String type2 = keysIterator.next().value;
                    if (type2.equals(mappingEntity.getType())) {
                        return result = true;
                    }
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建一个最基本的mapping
     * @param index
     * @param type
     */
    private void createDefaultMapping (String index, String type) {
        String map ="{\"" + type +  "\":{}}";
        logger.info("insert base mapping : index" + index + " - type: " + type );
        PutMappingRequest mappingRequest = Requests.putMappingRequest(index)
                .type(type)
                .source(map);
        this.getAdminClient().putMapping(mappingRequest).actionGet();
    }
}
