package pers.solax.Base.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by solax on 2017-5-11.
 */
public class MappingEntity {

    String index;

    String type;

    String parent;

    String routing;

    Map<String, Map<String, String>> properties = new HashMap<String, Map<String, String>>();

    public void addProperties (String field, Map<String, String> object) {
        properties.put(field, object);
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Map<String, String>> getProperties() {
        return properties;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void addIKAnalyzer (String field) {
        Map<String ,String> map = new HashMap<String, String>();
        map.put("type", "text");
        map.put("analyzer", "ik_max_word"); // 设置ik分词器
        this.addProperties(field, map);
    }
}
