package org.ifdc.web.dao.bean;

import java.util.HashMap;

/**
 *
 * @author Meng Zhang
 */
public class Project extends HashMap<String, String> {
    
    public Project(String projName) {
        super();
        this.put("name", projName);
    }
    
    public String getName() {
        return get("name");
    }
    
    public String setDescription(String description) {
        return put("description", description);
    }
    
    public String getDescription() {
        return get("description");
    }
    
}
