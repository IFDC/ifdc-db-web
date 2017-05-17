package org.ifdc.web.dao.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import spark.Request;

/**
 *
 * @author Meng Zhang
 */
public class Indicator extends HashMap<String, String> {
    
    public Indicator() {
        super();
    }
    
    public Indicator(Request request) {
        this.put("name", request.queryParams("name"));
        this.put("title", request.queryParams("title"));
        this.put("eg_code1", request.queryParams("eg_code1"));
        this.put("eg_code2", request.queryParams("eg_code2"));
        this.put("unit", request.queryParams("unit"));
    }
    
    public static Indicator toIndicator(Map m) {
        Indicator ret = new Indicator();
        ret.putAll(m);
        return ret;
    }
    
    public String getName() {
        return this.get("name");
    }
    
    public String getTitle() {
        return this.get("title");
    }
    
    public String getEgCode1() {
        return this.get("eg_code1");
    }
    
    public String getEgCode2() {
        return this.get("ed_code2");
    }
    
    public String getUnit() {
        String ret = this.get("unit");
        if (ret == null) {
            ret = "";
        }
        return ret;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Indicator) {
            Indicator c = (Indicator) o;
            if (c.getName() != null && this.getName() != null && c.getName().equals(this.getName())) {
                return true;
            } else if (c.getTitle() != null && this.getTitle() != null && c.getTitle().equals(this.getTitle())) {
                return true;
            } else {
                return false;
            }
//        } else if (o instanceof HashMap) {
//            Indicator c = (Indicator) o;
//            if (c.get("name") != null && this.get("name") != null && c.get("name").equals(this.get("name"))) {
//                return true;
//            } else if (c.get("title") != null && this.get("title") != null && c.get("title").equals(this.get("title"))) {
//                return true;
//            } else {
//                return false;
//            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int h = 0;
        Iterator<Entry<String, String>> i = entrySet().iterator();
        while (i.hasNext()) {
            Entry<String, String> entry = i.next();
            if (entry.getKey().equals("name")) {
                h += entry.hashCode();
                break;
            }
        }
        return h;
    }
}
