package org.ifdc.web.view;

import java.util.ArrayList;
import java.util.Map;
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
public class ProjectViewUtil {
    
    protected static void setProjectCommonParam(Request request, Map<String, Object> attributes) {
        if (!attributes.containsKey("name") && attributes.containsKey("project_name")) {
            attributes.put("name", attributes.get("project_name"));
        }
    }
    
    public static String getCreatePage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        setProjectCommonParam(request, attributes);
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Project.CREATE));
    }
    
    public static String getListPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        setProjectCommonParam(request, attributes);
        attributes.put("projects", ProjectDAO.list());
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Project.LIST));
    }
    
    public static String getDetailPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        setProjectCommonParam(request, attributes);
        attributes.put("avtivities", ActivityDAO.search((String) attributes.get("name")));
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Project.DETAIL));
    }
    
    
    public static String getSearchPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        setProjectCommonParam(request, attributes);
        attributes.put("projectNames", ProjectDAO.listNames());
        if (!attributes.containsKey("results")) {
            attributes.put("results", new ArrayList());
        }
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Project.CREATE));
    }
}
