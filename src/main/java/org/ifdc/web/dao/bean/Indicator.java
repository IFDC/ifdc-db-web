package org.ifdc.web.dao.bean;

import java.util.HashMap;

/**
 *
 * @author Meng Zhang
 */
public class Indicator extends HashMap<String, String> {
    
    public String getId() {
        return this.get("id");
    }
    
    public String getTitle() {
        return this.get("title");
    }
    
    public String getCode() {
        return this.get("code");
    }
    
    public String getUnit() {
        String ret = this.get("unit");
        if (ret == null) {
            ret = "";
        }
        return ret;
    }
}
