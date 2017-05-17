package org.ifdc.web.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import static org.ifdc.web.Main.LOG;
import org.ifdc.web.dao.ActivityDAO;
import org.ifdc.web.dao.ProjectDAO;
import org.ifdc.web.util.Path;
import static org.ifdc.web.view.ViewUtil.setCommonParam;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

/**
 *
 * @author Meng Zhang
 */
public class ActivityViewUtil {
    
    public static String getCreatePage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        String projName = request.queryParams("project_name");
        if (ProjectDAO.isNameExist(projName)) {
            attributes.put("project_name", projName);
        } else {
            attributes.put("projects", ProjectDAO.listNames());
        }
        attributes.put("crops", ActivityDAO.getCropList());
        attributes.put("technologies", ActivityDAO.getTechnologyList());
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Activity.CREATE));
    }
    
    public static String getListPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        attributes.put("avtivities", ActivityDAO.list());
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Activity.LIST));
    }
    

//    public static String getCreateDetailPage(Request request, Map<String, Object> attributes) {
//        
//        setCommonParam(request, attributes);
//        HashMap<String, HashMap<String, String>> indicatorValues = ActivityDAO.getIndicatorValues(attributes);
//        HashSet<String> indicators = new HashSet();
//        for (String key : indicatorValues.keySet()) {
//            indicators.addAll(indicatorValues.get(key).keySet());
//        }
//        attributes.put("years", indicatorValues.keySet());
//        attributes.put("indicators", indicators);
//        attributes.put("indicatorValues", indicatorValues);
//        attributes.put("allIndicators", ActivityDAO.getIndicatorList());
//        LOG.info(indicatorValues.toString());
//        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Activity.CREATE_DETAIL));
//    }
}
