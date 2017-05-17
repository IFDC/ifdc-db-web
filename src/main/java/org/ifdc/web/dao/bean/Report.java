package org.ifdc.web.dao.bean;

import java.util.HashMap;
import java.util.Map;
import spark.Request;

/**
 *
 * @author Meng Zhang
 */
public class Report extends HashMap<String, Object> {
    
    public Report(Request request) {
        this.put("project_name", request.queryParams("project_name"));
        this.put("crop", request.queryParams("crop"));
        this.put("tech", request.queryParams("tech"));
        this.put("year", request.queryParams("year"));
        this.put("indicators", request.queryParams("indicators"));
    }
    
    public Report(Activity activity, String year) {
        this(activity, year, new HashMap());
    }
    
    public static Report toReport(Map m) {
        Report ret = new Report(Activity.toActivity(m), (String) m.get("year"));
        ret.putAll(m);
        return ret;
    }
    
    public Report(Activity activity, String year, HashMap indicators) {
        super();
        this.putAll(activity);
        this.put("year", year);
        this.put("indicators", indicators);
    }
    
    public String getProject() {
        return (String) get("project_name");
    }
    
    public String getCrop() {
        return (String) get("crop");
    }
    
    public String getTech() {
        return (String) get("tech");
    }
    
    public String getYear() {
        return (String) get("year");
    }
    
    public HashMap<String, String> getIndicatorMap() {
        return (HashMap<String, String>) get("indicators");
    }
}
