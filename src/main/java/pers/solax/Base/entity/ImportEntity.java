package pers.solax.Base.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by solax on 2017-3-30.
 */
public class ImportEntity {

    List<String> templateList = new ArrayList<String>();

    public List<String> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<String> templateList) {
        this.templateList = templateList;
    }

    public void addTemplateList (String filePath) {
        this.templateList.add(filePath);
    }
}
