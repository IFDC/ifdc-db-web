package org.ifdc.web.dao.bean;

//import java.util.Arrays;
import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Meng Zhang
 */
public class Activity extends HashMap<String, String> {
    
//    private static final ConcurrentHashMap<String, Indicator> indicators = new ConcurrentHashMap();
//    
//    public List<Indicator> getIndicators() {
//        return Arrays.asList(indicators.values().toArray(new Indicator[]{}));
//    }
//    
//    public Indicator getIndicator(String indicatorId) {
//        return indicators.get(indicatorId);
//    }
    
    public String getProject() {
        return get("project");
    }
    
    public String getCrop() {
        return get("crop");
    }
    
    public String getTechnology() {
        return get("technology");
    }
    
    public String getYear() {
        return get("year");
    }
    
    public String getIndicatorValue(String indicatorId) {
        return getIndicatorValue(indicatorId, "");
    }

    public String getIndicatorValue(String indicatorId, String defVal) {
        String ret = this.get(indicatorId);
        if (ret == null) {
            return defVal;
        } else {
            return ret;
        }
    }
//    
//    
//    public boolean putIndicatorValue(String indicatorId, String indicatorValue) {
//        
//    }
}
