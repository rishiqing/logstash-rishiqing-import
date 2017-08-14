package pers.solax.Base.schedule;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import pers.solax.Base.entity.MappingEntity;
import pers.solax.Base.factory.MappingFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by solax on 2017-3-30.
 */
public abstract class MappingBase   extends ImportBase {

    private MappingFactory mappingFactory =  MappingFactory.getInstance();

    protected abstract void mapping();

    protected void setParent (String type, String parentType) {
        MappingEntity mappingEntity = new MappingEntity();
        mappingEntity.setType(type);
        mappingEntity.setParent(parentType);
        this.mappingFactory.addMapping(mappingEntity);
    }

    protected Map<String ,String> buildIKAnalyzer (String field) {
        Map<String ,String> map = new HashMap<String, String>();
        map.put("type", "text");
        map.put("analyzer", "ik_max_word"); // 设置ik分词器
        return  map;
    }


    protected void setMapping (MappingEntity mappingEntity) {
        this.mappingFactory.addMapping(mappingEntity);
    }
/*
    protected  void setIkAnalyzer (String type, String field) {
        this.mappingFactory.putIkAnalyzer(type, field);
    }*/

    public  void exec () {
        this.mapping();
    }
}
