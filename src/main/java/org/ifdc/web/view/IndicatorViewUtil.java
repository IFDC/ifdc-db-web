package org.ifdc.web.view;

import java.util.Map;
import org.ifdc.web.dao.IndicatorDAO;
import org.ifdc.web.util.Path;
import static org.ifdc.web.view.ViewUtil.setCommonParam;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

/**
 *
 * @author Meng Zhang
 */
public class IndicatorViewUtil {
    
    public static String getCreatePage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        attributes.put("units", IndicatorDAO.getUnitList());
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Indicator.CREATE));
    }
    
    public static String getEditPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        attributes.put("units", IndicatorDAO.getUnitList());
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Indicator.EDIT));
    }
    
    public static String getListPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        attributes.put("indicators", IndicatorDAO.list());
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Indicator.LIST));
    }
}
