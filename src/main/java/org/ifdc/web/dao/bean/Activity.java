package org.ifdc.web.dao.bean;

import java.util.HashMap;
import java.util.Map;
import spark.Request;

/**
 *
 * @author Meng Zhang
 */
public class Activity extends HashMap<String, String> {
    
    public Activity(Request request) {
        this.put("project_name", request.queryParams("project_name"));
        this.put("crop", request.queryParams("crop"));
        this.put("tech", request.queryParams("tech"));
    }
    
    public Activity(String projName, String crop, String tech) {
        super();
        this.put("project_name", projName);
        this.put("crop", crop);
        this.put("tech", tech);
    }
    
    public static Activity toActivity(Map m) {
        Activity ret = new Activity((String) m.get("project_name"), (String) m.get("crop"), (String) m.get("tech"));
        ret.putAll(m);
        return ret;
    }
    
    public String getProject() {
        return get("project_name");
    }
    
    public String getCrop() {
        return get("crop");
    }
    
    public String getTech() {
        return get("tech");
    }
}
